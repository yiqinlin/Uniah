package com.uniah.mobile.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Created by Stark on 2018/4/8.
 */

public class UniCookie {
    private static SharedPreferences sharedPreferences;

    private static void check(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        }
    }

    public static void putString(Context context, String key, @Nullable String value) {
        check(context);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static void putStringSet(Context context, String key, @Nullable Set<String> values) {
        check(context);
        sharedPreferences.edit().putStringSet(key, values).apply();
    }

    public static void putInt(Context context, String key, int value) {
        check(context);
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public static void putLong(Context context, String key, long value) {
        check(context);
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public static void putFloat(Context context, String key, float value) {
        check(context);
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        check(context);
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static SharedPreferences.Editor remove(Context context, String key) {
        check(context);
        return sharedPreferences.edit().remove(key);
    }

    public static SharedPreferences.Editor clear(Context context) {
        check(context);
        return sharedPreferences.edit().clear();
    }

    public static Map<String, ?> getAll(Context context) {
        check(context);
        return sharedPreferences.getAll();
    }

    public static String getString(Context context, String key, @Nullable String defValue) {
        check(context);
        return sharedPreferences.getString(key, defValue);
    }

    public static String getString(Context context, String key) {
        check(context);
        return sharedPreferences.getString(key, null);
    }

    public static Set<String> getStringSet(Context context, String key, @Nullable Set<String> defValues) {
        check(context);
        return sharedPreferences.getStringSet(key, defValues);
    }

    public static Set<String> getStringSet(Context context, String key) {
        check(context);
        return sharedPreferences.getStringSet(key, null);
    }

    public static int getInt(Context context, String key, int defValue) {
        check(context);
        return sharedPreferences.getInt(key, defValue);
    }

    public static int getInt(Context context, String key) {
        check(context);
        return sharedPreferences.getInt(key, -1);
    }

    public static long getLong(Context context, String key, long defValue) {
        check(context);
        return sharedPreferences.getLong(key, defValue);
    }

    public static long getLong(Context context, String key) {
        check(context);
        return sharedPreferences.getLong(key, -1);
    }

    public static float getFloat(Context context, String key, float defValue) {
        check(context);
        return sharedPreferences.getFloat(key, defValue);
    }

    public static float getFloat(Context context, String key) {
        check(context);
        return sharedPreferences.getFloat(key, -1);
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        check(context);
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static boolean getBoolean(Context context, String key) {
        check(context);
        return sharedPreferences.getBoolean(key, false);
    }

    public static boolean contains(Context context, String key) {
        check(context);
        return sharedPreferences.contains(key);
    }

    public static void registerOnSharedPreferenceChangeListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        check(context);
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public static void unregisterOnSharedPreferenceChangeListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener listener) {
        check(context);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
