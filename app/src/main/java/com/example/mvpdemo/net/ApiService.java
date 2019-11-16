package com.example.mvpdemo.net;



import com.example.mvpdemo.model.JokeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("text.from?key=ae240f7fba620fc370b803566654949e")
    Observable<JokeBean> getJokeData(
        @Query("page") int page,
        @Query("pagesize") int pagesize
    );
}
