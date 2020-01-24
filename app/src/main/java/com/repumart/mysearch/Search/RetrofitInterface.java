package com.repumart.mysearch.Search;

import com.repumart.mysearch.Search.SearchModel.Rank;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface
{
    @GET("posts")
    Call<List<User>> getUsers();

    @GET("api/v1/search")
       Call<Rank> getRanking(@Query("keyword") String keyword);
}



