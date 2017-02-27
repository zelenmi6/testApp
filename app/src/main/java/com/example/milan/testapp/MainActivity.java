package com.example.milan.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import com.example.milan.testapp.json.TvShow;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import webServices.TvMazeService;
import webServices.TvMazeServiceInterface;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_of_shows) RecyclerView showsView;
    @BindView(R.id.load_shows_button) Button loadShowsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * Load TV Shows clicked
     */
    @OnClick(R.id.load_shows_button)
    public void loadShows() {
        loadShowsButton.setEnabled(false);
        getTvShows();
    }

//    /**
//     * Downloads TV show info.
//     * Obsolete and no longer used.
//     */
//    private void getTvShows() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.tvmaze.com/")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        TvMazeServiceInterface service = retrofit.create(TvMazeServiceInterface.class);
//
//        Observable<List<TvShow>> tvShows = service.listAllShowsRX();
//
//        tvShows.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(tvShow -> {
//                    // Display information on screen
//                    populateShowList(tvShow);
//                });
//    }

    /**
     * Downloads TV show info.
     */
    private void getTvShows() {
        TvMazeService tvMazeService = new TvMazeService();
        tvMazeService.listAllShowsRX(tvShows -> populateShowList(tvShows));
    }

    /**
     * Displays shows on screen
     * @param tvShows List with TV show information
     */
    public void populateShowList(List<TvShow> tvShows) {
        showsView.setHasFixedSize(true);
        showsView.setLayoutManager(new LinearLayoutManager(this));
        TVShowAdapter adapter = new TVShowAdapter(this, tvShows);
        showsView.setAdapter(adapter);
    }

    private void testRetrofitNewThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.tvmaze.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                TvMazeServiceInterface service = retrofit.create(TvMazeServiceInterface.class);

                Call<List<TvShow>> allShowsCall = service.listAllShows();
                Response<List<TvShow>> allShowsResponse = null;
                try {
                    allShowsResponse = allShowsCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Debug", "failure");
                    return;
                }
                List<TvShow> showList = allShowsResponse.body();
                Log.d("Debug", "shows acquired");
            }
        };

        thread.start();
    }
}


















