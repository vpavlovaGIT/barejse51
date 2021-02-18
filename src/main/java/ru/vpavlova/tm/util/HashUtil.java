package ru.vpavlova.tm.util;

public interface HashUtil {

    String SECRET = "2564198031";

    Integer ITERATION = 78435;

    static String salt(final String value) {
        if (value == null) return null;
        String result = value;
        for (int i = 0; i < ITERATION; i++) {
            result = md5(SECRET + result + SECRET);
        }
        return result;
    }

    static String md5(final String value) {
        if (value == null) return null;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(value.getBytes());
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
