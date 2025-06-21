package com.studentgrades.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {

    public static String normalizeForComparison(String input) {
        return input == null ? null : input.trim().replaceAll("\\s+", " ").toLowerCase();
    }

    public static String formatName(String input) {
        if (input == null) return null;

        return Arrays.stream(input.trim().toLowerCase().split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
