package com.project.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author pengzhiqun
 * @Date 2017/2/22.15:04
 * @Discription AES加密解密
 */
public class AESUtil {
    /**
     *
     * @param content 需要加密的内容
     * @param password 加密秘钥
     * @return
     */
    public static byte[] encrypt(String content, String password){
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param content 需要解密内容
     * @param password  解密秘钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 返回解密字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二进制转16
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 16进制转二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * Android 密码解密
     * @param content
     * @param key
     * @return
     */
//    public static String decrypt(String content, String key) {
//        String result = "";
//        try {
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//            byte[] keyBytes = new byte[16];
//            byte[] b = key.getBytes("utf-8");
//            int len = b.length;
//            if (len > keyBytes.length)
//                len = keyBytes.length;
//            System.arraycopy(b, 0, keyBytes, 0, len);
//            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
//            cipher.init(Cipher.DECRYPT_MODE, keySpec);
//            byte[] results = cipher.doFinal(Base64.decodeBase64(content.getBytes()));
//            result = new String(results, "utf-8");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
