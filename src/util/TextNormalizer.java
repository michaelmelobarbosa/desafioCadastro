package util;

import java.text.Normalizer;
import java.util.Locale;

public class TextNormalizer {

    public static String normalize(String entrada){
        return Normalizer.normalize(entrada.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase(Locale.ROOT);
    }
}
