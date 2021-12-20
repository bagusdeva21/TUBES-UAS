package com.salon.salonwawa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.com.crosp.solutions.library.prettytoast.PrettyToast;

public class Backend {

    private String url="https://test-map-329000-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private final String TAG = "Backend";
    private Context context;

    public Backend(){}
    public Backend(Context context) {
        this.context = context;
    }

    public FirebaseUser cekUserAktif() {
        FirebaseApp.initializeApp(context);
        mAuth = FirebaseAuth.getInstance();
        return mAuth.getCurrentUser();
    }

    public void register(User user) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance(url).getReference();
        mAuth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            PrettyToast.showSuccess(context, "Cek email untuk aktivasi");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(user.username)
                                    .build();
                            firebaseUser.updateProfile(profileUpdates);
                            String userId = firebaseUser.getUid();
                            mDatabase.child("users").child(userId).setValue(user);
                            firebaseUser.sendEmailVerification();
                            ((Activity) context).onBackPressed();
                        } else {
                            Log.e(TAG, "createUserWithEmail:failure", task.getException());
                            PrettyToast.showError(context, "Error ");
                        }
                    }
                });
    }

    public void login(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance(url).getReference();
        if (email.equals("") || password.equals(""))
            PrettyToast.showWarning(context, "Isi semua data");
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                if (firebaseUser.isEmailVerified()) {
                                    retroGet();
                                    PrettyToast.showSuccess(context, "Selamat datang " + firebaseUser.getDisplayName());
                                } else {
                                    PrettyToast.showWarning(context, "Harap verifikasi akun melalui email");
                                }
                            } else {
                                Log.e(TAG, "signin:failure", task.getException());
                                PrettyToast.showError(context, task.getException().getMessage());
                            }
                        }
                    });
        }
    }

    public void logout() {
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("EXIT", true);
        context.startActivity(i);
        FirebaseAuth.getInstance().signOut();
    }

    public void update(String displayName){
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .build();
        firebaseUser.updateProfile(profileUpdates);
    }

    public void retroGet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<HashMap<String, User>> call2 = api.getData();
        call2.enqueue(new Callback<HashMap<String, User>>() {
            @Override
            public void onResponse(Call<HashMap<String, User>> call, Response<HashMap<String, User>> response) {
                String id=mAuth.getCurrentUser().getUid();
                Data.user.nama = response.body().get(id).nama;
                Data.user.umur = response.body().get(id).umur;
                Data.user.noTelp = response.body().get(id).noTelp;
                Data.user.email = response.body().get(id).email;
                Data.user.username = response.body().get(id).username;
                Data.user.password = response.body().get(id).password;
                context.startActivity(new Intent(context, HomeActivity.class));
                ((Activity)context).onBackPressed();
            }

            @Override
            public void onFailure(Call<HashMap<String, User>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    public void retroGetInvoice() {
        mAuth=FirebaseAuth.getInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<HashMap<String, Invoice>> call2 = api.getInvoice();
        call2.enqueue(new Callback<HashMap<String, Invoice>>() {
            @Override
            public void onResponse(Call<HashMap<String, Invoice>> call, Response<HashMap<String, Invoice>> response) {
                String id=mAuth.getCurrentUser().getUid();
                try{
                    Invoice invoice=new Invoice(
                            response.body().get(id).hari,
                            response.body().get(id).tanggal,
                            response.body().get(id).tukangCukur,
                            response.body().get(id).pembayaran);
                    Data.invoice=invoice;
                    context.startActivity(new Intent(context, InvoiceActivity.class));
                }
                catch (NullPointerException e){
                    PrettyToast.showError(context,"Belum pernah pesan salon");
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, Invoice>> call, Throwable t) {
                PrettyToast.showError(context,"Gagal get retrofit");
            }
        });
    }

    public void retroPutUser(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        String id=mAuth.getCurrentUser().getUid();
        Call<User> call2 = api.putUser(id,user);
        call2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                PrettyToast.showSuccess(context, "Sukses");
                ((Activity) context).onBackPressed();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
    public void retroPutInvoice(Invoice invoice) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Invoice> call2 = api.putInvoice(FirebaseAuth.getInstance().getUid(),invoice);
        call2.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                context.startActivity(new Intent(context, InvoiceActivity.class));
                ((Activity)context).onBackPressed();
            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

}
