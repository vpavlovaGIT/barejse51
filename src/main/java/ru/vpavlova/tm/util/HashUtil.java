package ru.vpavlova.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HashUtil {

    @NotNull String SECRET = "2564198031";

    @NotNull Integer ITERATION = 78435;

    @Nullable
    static String salt(@Nullable final String value) {
        if (value == null) return null;
        @Nullable String result = value;
        for (int i = 0; i < ITERATION; i++) {
            result = md5(SECRET + result + SECRET);
        }
        return result;
    }

    @Nullable
    static String md5(@Nullable final String value) {
        if (value == null) return null;
        try {
            @NotNull java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            @NotNull final byte[] array = md.digest(value.getBytes());
            @NotNull final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (@NotNull java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
