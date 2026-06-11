package util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringNormalizer {
    private static final Pattern DIACRITICAL_MARKS_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    public static String normalize(String input) {
        if (input == null) {
            return "";
        }
        // Decompose accents
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Remove accents
        String result = DIACRITICAL_MARKS_PATTERN.matcher(temp).replaceAll("");
        // Replace Vietnamese đ/Đ which is not decomposed by Normalizer Form NFD
        result = result.replace("đ", "d").replace("Đ", "D");
        return result.trim().toLowerCase();
    }
}
