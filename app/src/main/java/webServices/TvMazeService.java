package webServices;

import android.content.Context;

import com.example.milan.testapp.json.TvShow;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Milan on 2/27/2017.
 */

public class TvMazeService {
    private TvMazeServiceInterface service;

    public TvMazeService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(TvMazeServiceInterface.class);
    }

    /**
     * Not implemented
     */
    public void listAllShows(Consumer<List<TvShow>> block) {
    }

    /**
     *
     * @param block Callback function to be called when results are ready
     */
    public void listAllShowsRX(Consumer<List<TvShow>> block) {
        Observable<List<TvShow>> tvShows = service.listAllShowsRX();
        tvShows.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(tvShow -> {
                    block.accept(tvShow);
                });
    }
}
