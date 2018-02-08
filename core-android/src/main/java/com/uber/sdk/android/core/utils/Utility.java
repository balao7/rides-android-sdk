package com.uber.sdk.android.core.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.uber.sdk.android.core.UberSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility {
    private static final String HASH_ALGORITHM_SHA1 = "SHA-1";


    public static String sha1hash(String key) {
        return hashWithAlgorithm(HASH_ALGORITHM_SHA1, key);
    }

    public static String sha1hash(byte[] bytes) {
        return hashWithAlgorithm(HASH_ALGORITHM_SHA1, bytes);
    }

    /**
     * Detects if the Application is currently in a Debug state
     */
    public static boolean isDebugable(@NonNull Context context) {
        return ( 0 != ( context.getApplicationInfo().flags & ApplicationInfo
                .FLAG_DEBUGGABLE ) );

    }

    private static String hashWithAlgorithm(String algorithm, String key) {
        return hashWithAlgorithm(algorithm, key.getBytes());
    }

    private static String hashWithAlgorithm(String algorithm, byte[] bytes) {
        MessageDigest hash;
        try {
            hash = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return hashBytes(hash, bytes);
    }

    private static String hashBytes(MessageDigest hash, byte[] bytes) {
        hash.update(bytes);
        byte[] digest = hash.digest();
        StringBuilder builder = new StringBuilder();
        for (int b : digest) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString((b >> 0) & 0xf));
        }
        return builder.toString();
    }
}
