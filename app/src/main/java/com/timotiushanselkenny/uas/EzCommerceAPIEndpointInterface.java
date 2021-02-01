package com.timotiushanselkenny.uas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EzCommerceAPIEndpointInterface {
    @GET("book/{bookId}/?nim=2201747640&nama=TimotiusHanselKenny")
    Call<Products> getBookDetail(@Path("bookId") String id);
    @GET("book?nim=2201747640&nama=TimotiusHanselKenny")
    Call<Products> getBook();
}
