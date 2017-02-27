package webServices;

import java.util.List;

import io.reactivex.Observable;
import com.example.milan.testapp.json.TvShow;

import retrofit2.*;
import retrofit2.http.GET;

/**
 * Created by Milan on 2/20/2017.
 */

public interface TvMazeServiceInterface {
    @GET("schedule/full")
    Call<List<TvShow>> listAllShows();

    @GET("schedule/full")
    Observable<List<TvShow>> listAllShowsRX();
}
