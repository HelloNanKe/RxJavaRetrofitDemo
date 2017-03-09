package com.example.nanke.rxjavaretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nanke.rxjavaretrofit.JavaBean.MovieEntity;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitle;
    private Button btn;
    private Observer<MovieEntity> observer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }


    private void getMovie() {

        observer = new Observer<MovieEntity>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                tvTitle.setText(movieEntity.getTitle());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
        HttpMethods.getInstance().getTopMovie(observer, 0, 10);
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this, "点击了按钮", Toast.LENGTH_SHORT).show();
        getMovie();
    }
}
