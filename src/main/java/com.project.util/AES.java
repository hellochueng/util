package com.project.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.SecretKeySpec;


public class AES{  
	
	public static final String strKey = "zm123wine.cn2016";
	
  
    /** 
     *            密钥
     * @param clearPwd 
     *            明文字符串 
     * @return 密文字节数组 
     */  
    public static String encrypt(String clearPwd) {  
        try {  
        	byte[] rawKey = getRawKey(strKey.getBytes()); 
            SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");  
            Cipher cipher = Cipher.getInstance("AES");  
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);  
            byte[] encypted = cipher.doFinal(clearPwd.getBytes());
            String asB64 = new Base64().byteArrayToAltBase64(encypted);
            return asB64;  
        } catch (Exception e) {  
            return null;  
        }
    }  
  
    /** 
     * @param encrypted 
     *            密文字节数组 
     * @return 解密后的字符串
     */  
    public static String decrypt(byte[] encrypted) {  
        try {  
        	byte[] rawKey = getRawKey(strKey.getBytes());  
            SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");  
            Cipher cipher = Cipher.getInstance("AES");  
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);  
            byte[] decrypted = cipher.doFinal(encrypted);  
            return new String(decrypted);  
        } catch (Exception e) {  
            return "";  
        }  
    }  
  
    /** 
     * @param seed
     * @return 密钥数据 
     */  
    private static byte[] getRawKey(byte[] seed) {  
        byte[] rawKey = null;  
        try {  
            KeyGenerator kgen = KeyGenerator.getInstance("AES");  
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");  
            secureRandom.setSeed(seed);  
            // AES加密数据块分组长度必须为128比特，密钥长度可以是128比特、192比特、256比特中的任意一个  
            kgen.init(128, secureRandom);  
            SecretKey secretKey = kgen.generateKey();  
            rawKey = secretKey.getEncoded();  
        } catch (NoSuchAlgorithmException e) {  
        }  
        return rawKey;  
    } 
    
    /**
     * 将二进制转换成16进制
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
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
     * 将16进制转换为二进制
     * 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}  