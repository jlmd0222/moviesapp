package jlmd.dev.android.services;

import java.util.List;

import jlmd.dev.android.dataaccess.MovieDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET(Constants.GET_MOVIES)
    Call<MovieDTO.MoviesNode> getDataMovies(@Query("api_key") String api_key);
}
