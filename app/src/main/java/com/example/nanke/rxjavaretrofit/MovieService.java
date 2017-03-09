package com.example.nanke.rxjavaretrofit;

import com.example.nanke.rxjavaretrofit.JavaBean.MovieEntity;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zt on 2017/3/8.
 */

public interface MovieService {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
