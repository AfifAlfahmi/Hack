package com.afif.hack;

import android.os.Build;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Utils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5PADDING";
    private static final int KEY_SIZE= 256;
    private static final String ENC_KEY = "AC02F28884A69E36669409AD47A58A5AC938C4236DFDA946178A8F22FF5AABC3";
    private static final String flag4 = "57CFAA693ED024F9E0395F1C99571110F9D3C59595B820FE68509FD074C1E4B5";
    private static final String flag5 = "64F4E9FD7BD11090EBA6A7CD4F76C015C24345713E783150D0FEAF7575155EA8";


    public static String getFlag4() {
        String result = "";
            result =  decryptFlag(4);

        return result;
    }

    public static String getFlag5() {
        String result = "";
        result =  decryptFlag(5);

        return result;
    }

    public static String decryptFlag(int flagNum){
        String decryptString = "";
        byte[] cipherText = null;
        if (flagNum == 4 ){
            cipherText = hexToBytes(flag4);
        }
        else if(flagNum == 5){
            cipherText = hexToBytes(flag5);
        }

        SecretKey secretKey = generateSecretKey();
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptString = new String(cipher.doFinal(cipherText));


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return decryptString;
    }

    public static SecretKey generateSecretKey()
    {
        byte[] keyBytes = hexToBytes(ENC_KEY);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    private static byte[] hexToBytes(String hex){
        int len = hex.length();
        byte[] result = new byte[len / 2];
        for (int i = 0;i < len;i += 2){
            result[i / 2] = (byte) ((Character.digit(hex.charAt(i),16) << 4)
            + Character.digit(hex.charAt(i+1),16));
        }
        return result;

    }

}
