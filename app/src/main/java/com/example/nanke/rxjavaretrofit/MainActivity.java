package com.example.nanke.rxjavaretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nanke.rxjavaretrofit.JavaBean.MovieEntity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tvTitle= (TextView) findViewById(R.id.tv_title);
         btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }


    private void getMovie() {

        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieEntity> call = movieService.getTopMoive(0, 10);
        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                tvTitle.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                Log.d("MAIN", "网络请求失败");
            }
        });
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this, "点击了按钮", Toast.LENGTH_SHORT).show();
        getMovie();
    }
}
