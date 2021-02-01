package com.timotiushanselkenny.uas;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EzEcommerceAPIClient {
    public static final String BASE_URL = "https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/staging/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
