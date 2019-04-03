package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.model.ShoppingCard;
import com.example.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class DBHelper {
    private static final String APP_SHARE = "APP_SHARE";
    private static final String LOGIN_INFO = "LOGIN_INFO";
    private static final String SHOPPING_CARD = "SHOPPING_CARD";

    public static void saveLoinInfo(Context ctx, UserModel userInfo) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(APP_SHARE, MODE_PRIVATE).edit();
        if (userInfo != null) {
            Gson gson = new Gson();
            String json = gson.toJson(userInfo);
            editor.putString(LOGIN_INFO, json);
        } else {
            editor.putString(LOGIN_INFO, "");
        }

        editor.apply();
    }

    public static UserModel getLoginInfo(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(APP_SHARE, MODE_PRIVATE);
        String data = prefs.getString(LOGIN_INFO, "");
        if (TextUtils.isEmpty(data)) {
            return null;
        } else {
            Gson gson = new Gson();
            return gson.fromJson(data, UserModel.class);
        }
    }

    public static void saveCard(Context ctx, ShoppingCard card) {
        // lay len
        SharedPreferences prefs = ctx.getSharedPreferences(APP_SHARE, MODE_PRIVATE);
        String data = prefs.getString(SHOPPING_CARD, "");

        // them vo
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ShoppingCard>>() {
        }.getType();
        ArrayList<ShoppingCard> lastCards = gson.fromJson(data, type);
        if (lastCards == null)
            lastCards = new ArrayList<>();
        boolean isExist = false;
        for (int i = 0; i < lastCards.size(); i++) {
            if (lastCards.get(i).getId().equalsIgnoreCase(card.getId())) {
                ShoppingCard item = lastCards.get(i);
                item = card;
                isExist = true;
            }

        }
        if (!isExist)
            lastCards.add(card);

        // luu xuong
        SharedPreferences.Editor editor = ctx.getSharedPreferences(APP_SHARE, MODE_PRIVATE).edit();
        editor.putString(SHOPPING_CARD, gson.toJson(lastCards));
        editor.apply();
    }

    public static ArrayList<ShoppingCard> getAllCard(Context ctx) {
        // lay len
        SharedPreferences prefs = ctx.getSharedPreferences(APP_SHARE, MODE_PRIVATE);
        String data = prefs.getString(SHOPPING_CARD, "");
        if (TextUtils.isEmpty(data)) {
            return new ArrayList<ShoppingCard>();
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ShoppingCard>>() {
            }.getType();
            return gson.fromJson(data, type);
        }

    }

    public static ShoppingCard getCard(Context ctx, String id) {
        // lay len
        SharedPreferences prefs = ctx.getSharedPreferences(APP_SHARE, MODE_PRIVATE);
        String data = prefs.getString(SHOPPING_CARD, "");
        if (TextUtils.isEmpty(data)) {//
            return null;
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ShoppingCard>>() {
            }.getType();
            ArrayList<ShoppingCard> lastCards = gson.fromJson(data, type);
            for (int i = 0; i < lastCards.size(); i++) {
                if (lastCards.get(i).getId().equalsIgnoreCase(id)) {
                    return lastCards.get(i);
                }

            }
            return null;
        }

    }

    public static void deleteCard(Context ctx, String id) {
        // lay len
        SharedPreferences prefs = ctx.getSharedPreferences(APP_SHARE, MODE_PRIVATE);
        String data = prefs.getString(SHOPPING_CARD, "");
        if (TextUtils.isEmpty(data)) {
            return;
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ShoppingCard>>() {
            }.getType();
            ArrayList<ShoppingCard> lastCards = gson.fromJson(data, type);
            for (int i = 0; i < lastCards.size(); i++) {
                if (lastCards.get(i).getId().equalsIgnoreCase(id)) {
                    lastCards.remove(i);
                    break;
                }

            }
            // luu xuong
            SharedPreferences.Editor editor = ctx.getSharedPreferences(APP_SHARE, MODE_PRIVATE).edit();
            editor.putString(SHOPPING_CARD, gson.toJson(lastCards));
            editor.apply();
        }

    }

}
