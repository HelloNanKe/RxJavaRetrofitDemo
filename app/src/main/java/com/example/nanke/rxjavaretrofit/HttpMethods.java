package com.example.nanke.rxjavaretrofit;

import com.example.nanke.rxjavaretrofit.JavaBean.MovieEntity;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apple on 2017/3/8.
 */

public class HttpMethods {
    private static final String BASEURL = "https://api.douban.com/v2/movie/";
    private static final int DEFULT_TIMEOUT = 5;

    private MovieService movieService;
    private Retrofit retrofit;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(DEFULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        movieService = retrofit.create(MovieService.class);
    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANSE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANSE;
    }

    public void getTopMovie(Observer<MovieEntity> subscriber, int start, int count) {

        movieService.getTopMovie(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
