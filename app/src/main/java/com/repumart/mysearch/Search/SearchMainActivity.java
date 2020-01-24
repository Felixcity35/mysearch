package com.repumart.mysearch.Search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.ProgressBar;
import android.widget.Toast;
import com.repumart.mysearch.R;
import com.repumart.mysearch.Search.SearchModel.Data;
import com.repumart.mysearch.Search.SearchModel.Rank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchMainActivity extends AppCompatActivity {

    RecyclerView rank_recycler ;
    private CustomAdapter customAdapter;
    private ArrayList<Data> userList;
    private Retrofit retrofit;
    SearchView newsearchview ;
    ProgressBar searchprogbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        rank_recycler = findViewById(R.id.rank_recycler_view);
        newsearchview = findViewById(R.id.newsearch);
        searchprogbar = findViewById(R.id.searchprogressbar);



        newsearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                try {
                             //pass the search query to retrofit
                    getUserListFromRestApi(s);
                              // pass the same search query again for filtering so i think this part make it slow.
                    customAdapter.getFilter().filter(s);


                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

        rank_recycler.setLayoutManager(new LinearLayoutManager(this));
        rank_recycler.setItemAnimator(new DefaultItemAnimator());


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request newRequest = chain.request().newBuilder()
                        //   .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .callTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(logging).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();

    }

    private void getUserListFromRestApi(String query) {

        searchprogbar.setVisibility(View.VISIBLE);


          RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Rank> call = retrofitInterface.getRanking(query);
        call.enqueue(new Callback<Rank>() {
            @Override
            public void onResponse(Call<Rank> call, Response<Rank> response) {

                userList = new ArrayList<Data>(response.body().getData());
                customAdapter = new CustomAdapter(getApplicationContext(), userList, new CustomItemClickListener() {

                    @Override
                    public void onItemClick(User user, int position) {
                        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                    }
                });
                searchprogbar.setVisibility(View.GONE);
                rank_recycler.setAdapter(customAdapter);

            }
            @Override
            public void onFailure(Call<Rank> call, Throwable t) {


            }
        });

    }

}
