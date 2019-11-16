package com.example.mvpdemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpdemo.presenter.BasePresenter;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        /*mPresenter = createPresenter();
        mPresenter.attachView((V)this);*/
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID){
        super.setContentView(layoutResID);
        findViews();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    //    mPresenter.detachView();
    }

    protected abstract T createPresenter();

    protected abstract void findViews();

    protected abstract void init();
}
