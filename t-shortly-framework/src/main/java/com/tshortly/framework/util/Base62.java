package com.tshortly.framework.util;

public final class Base62 {
    private Base62() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    private static final char[] CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static String encode(long number) {
        StringBuilder stringBuilder = new StringBuilder();
        while(number > 0) {
            int mod = (int) (number % 62);
            stringBuilder.append(CHARS[mod]);
            number /= 62;
        }
        return stringBuilder.reverse().toString();
    }

}