package ru.vpavlova.tm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.ISaltSetting;

public interface HashUtil {

    static String salt(
            @Nullable final ISaltSetting setting,
            @Nullable final String value
    ) {
        if (setting == null) return null;
        @Nullable String secret = setting.getPasswordSecret();
        @Nullable Integer iteration = setting.getPasswordIteration();
        return salt(secret, iteration, value);
    }

    @Nullable
    static String salt(
            @Nullable final String secret,
            @Nullable final Integer iteration,
            @Nullable final String value
    ) {
        if (value == null) return null;
        @Nullable String result = value;
        for (int i = 0; i < iteration; i++) {
            result = md5(secret + result + secret);
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
            for (int i = 1; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (@NotNull java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    @SneakyThrows
    static String salt(
            @Nullable final ISaltSetting setting,
            @Nullable final Object value
    ) {
        if (setting == null) return null;
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final String json = objectMapper.writeValueAsString(value);
        @Nullable final String secret = setting.getSignSecret();
        @Nullable final Integer iteration = setting.getSignIteration();
        return salt(secret, iteration, json);
    }

}