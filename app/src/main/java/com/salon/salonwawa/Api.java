package com.salon.salonwawa;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @GET("/users.json")
    Call<HashMap<String,User>> getData();
    @PUT("/users/{id}.json")
    Call<User> putUser(@Path("id") String id, @Body User user);
    @GET("/invoice.json")
    Call<HashMap<String,Invoice>> getInvoice();
    @PUT("/invoice/{id}.json")
    Call<Invoice> putInvoice(@Path("id") String id, @Body Invoice resi);


}
