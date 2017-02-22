package com.example.milan.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import json.TvShow;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import webServices.TvMazeService;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_of_shows) ListView showsView;
    @BindView(R.id.load_shows_button) Button loadShowsButton;
    List<TvShow> tvShows = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        testRetrofitRX();
//        testRetrofitNewThread();
    }

    /**
     * Particular TV show was clicked
     * @param position Show's position in the list
     */
    @OnItemClick(R.id.list_of_shows)
    void onListItemClick(int position) {
        Log.d("Debug", "position: " + position);
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("SHOW_INFORMATION", tvShows.get(position).get_embedded().getShowDetails());
        startActivity(intent);
    }

    /**
     * Load TV Shows clicked
     */
    @OnClick(R.id.load_shows_button)
    public void loadShows() {
        loadShowsButton.setEnabled(false);
        tvShows = null;
        getTvShows();
    }

    /**
     * Downloads TV show info
     */
    private void getTvShows() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TvMazeService service = retrofit.create(TvMazeService.class);

        Observable<List<TvShow>> tvShows = service.listAllShowsRX();

        tvShows.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(tvShow -> {
                    // Display information on screen
                    populateShowList(tvShow);
                });
    }

    /**
     * Displays shows on screen
     * @param tvShows List with TV show information
     */
    public void populateShowList(List<TvShow> tvShows) {
        this.tvShows = tvShows;
        tvShows.forEach(show->Log.e("Show: " + show.getId(), show.getName()));
        ArrayAdapter adapter = new ArrayAdapter<TvShow>(this, R.layout.list_item, tvShows.toArray(new TvShow[0]));
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
                TvMazeService service = retrofit.create(TvMazeService.class);

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


















