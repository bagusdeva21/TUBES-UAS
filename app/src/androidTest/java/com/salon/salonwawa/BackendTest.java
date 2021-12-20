package com.salon.salonwawa;

import android.content.Intent;

import junit.framework.TestCase;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.com.crosp.solutions.library.prettytoast.PrettyToast;

public class BackendTest extends TestCase {

    public void testRetroGetInvoice() {
        String url="https://test-map-329000-default-rtdb.asia-southeast1.firebasedatabase.app/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<HashMap<String, Invoice>> call2 = api.getInvoice();
        call2.enqueue(new Callback<HashMap<String, Invoice>>() {
            @Override
            public void onResponse(Call<HashMap<String, Invoice>> call, Response<HashMap<String, Invoice>> response) {
                String id="test";
                assertEquals("Senin",response.body().get(id).hari);
            }
            @Override
            public void onFailure(Call<HashMap<String, Invoice>> call, Throwable t) {
            }
        });
    }
}