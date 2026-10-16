# Correções e observações

Este documento registra pontos para estudar e corrigir depois. **As sugestões abaixo ainda não foram aplicadas ao código.**

## Estado atual

- A seleção de critérios aceita de um a dois números distintos entre `1` e `6`; entradas inválidas fazem o menu ser exibido novamente.
- A leitura de sexo repete a pergunta até receber `macho` ou `fêmea` (também aceita sem acento e em diferentes combinações de maiúsculas/minúsculas).
- A leitura de `Double` repete a entrada quando o texto não pode ser convertido e aceita vírgula decimal.
- O projeto compilou com `javac` na última verificação. Isso confirma compilação, mas não cobre todos os comportamentos em execução.

## 1. Não aceitar texto vazio em um critério escolhido

Em `PetController.lerEntrada()`, uma linha vazia atualmente vira `null`. Como o repositório interpreta `null` como “não aplicar este filtro”, o usuário pode escolher nome, raça ou endereço, apertar Enter e acabar fazendo uma busca sem esse critério.

Se um critério foi escolhido, prefira repetir a pergunta até receber texto não vazio:

```java
private String lerEntrada(String mensagem) {
    while (true) {
        System.out.print(mensagem);
        String entrada = sc.nextLine().trim();

        if (!entrada.isBlank()) {
            return entrada;
        }

        System.out.println("A entrada não pode ficar vazia. Tente novamente.");
    }
}
```

## 2. Validar os valores numéricos depois do parse

`Double.parseDouble()` aceita `NaN`, `Infinity`, `-Infinity` e valores negativos. Para idade e peso, provavelmente só são válidos valores finitos e não negativos.

Depois de converter, valide o valor antes de retorná-lo:

```java
double valor = Double.parseDouble(input);
if (!Double.isFinite(valor) || valor < 0) {
    System.out.println("Digite um valor finito e não negativo.");
    continue;
}
return valor;
```

Mantenha também o `catch (NumberFormatException)` para entradas que não são números, como `abc` ou uma linha vazia. O tratamento fica dentro do `while` para permitir uma nova tentativa.

## 3. Padronizar as leituras do `Scanner`

O cadastro usa `next()`/`nextDouble()`, enquanto a busca usa `nextLine()`. Métodos como `nextDouble()` deixam o restante da linha (incluindo o newline) no scanner. Se cadastro e busca forem executados em sequência, a primeira leitura com `nextLine()` pode consumir esse restante vazio e gerar uma tentativa extra.

Uma solução consistente é usar `nextLine()` para todas as entradas e converter explicitamente os valores numéricos. Também permite nomes e campos com espaços. Outra solução temporária é consumir o restante da linha ao fim do cadastro, mas padronizar as leituras é mais simples de manter.

## 4. Busca por endereço com texto composto — pendente

O controller já lê o endereço como uma linha completa. Porém, o `PetRepository.contemEndereco()` ainda compara o termo inteiro com rua, número e cidade separadamente. Assim, uma consulta como `rua jose luiz, numero 4` não necessariamente corresponde a nenhum campo individual.

Quando decidir implementar essa busca, tokenize o termo e aplique OR entre os tokens e os componentes do endereço. Como melhoria futura, ignore palavras genéricas (*stopwords*) que não ajudam a identificar o endereço, por exemplo `rua`, `avenida`, `av`, `cidade` e `numero`. Assim, numa busca como `avenida jose silva, numero 4`, os termos úteis seriam `jose`, `silva` e `4`. A lista definitiva de stopwords ainda fica para decidir depois.

## 5. `default` defensivo no switch dos critérios

`lerEscolhasBusca()` já valida a string como um ou dois caracteres entre `1` e `6`, então o `default` do switch em `buscaPet()` não deve ser alcançado por entrada normal do usuário.

Se quiser manter uma proteção para detectar uma inconsistência interna, use `IllegalStateException`, pois o valor já deveria ter passado pela validação. Alternativamente, o `default` pode ser removido enquanto a validação anterior for mantida.

## 6. Limpeza de código

- O campo `petDto` e o parâmetro correspondente do construtor de `PetController` não são usados; o DTO é criado localmente em `buscaPet()`. Considere removê-los.
- Remova imports sem uso em `PetController`, como `javax.sound.midi.Soundbank`, `java.sql.SQLOutput` e `util.LeitorDeArquivo`.
- Prefira imports específicos a `java.util.*` quando fizer a limpeza.

## Separação de responsabilidades

- **Controller:** leitura e repetição de entradas do CLI, menus, conversão de respostas para tipos (`Sexo`, `Tipo`, números) e apresentação dos resultados.
- **Service:** validação das regras do caso de uso, como os critérios obrigatórios e os limites válidos.
- **Repository:** carregamento dos pets e aplicação dos filtros, incluindo a comparação textual da busca.
