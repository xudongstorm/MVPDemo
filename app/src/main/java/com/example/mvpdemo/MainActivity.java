package com.example.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvpdemo.activity.BaseActivity;
import com.example.mvpdemo.model.JokeBean;
import com.example.mvpdemo.net.ApiClient;
import com.example.mvpdemo.net.ApiService;
import com.example.mvpdemo.presenter.BasePresenter;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtRequestJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void findViews() {
        mBtRequestJoke = findViewById(R.id.bt_request_joke);
        mBtRequestJoke.setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.bt_request_joke){
            ApiClient.getInstance()
                    .create(ApiService.class)
                    .getJokeData(1, 8)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<JokeBean>() {
                        @Override
                        public void accept(JokeBean jokeBean) throws Exception {
                            JokeBean bean = jokeBean;
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Throwable error = throwable;
                        }
                    });

        }
    }
}
