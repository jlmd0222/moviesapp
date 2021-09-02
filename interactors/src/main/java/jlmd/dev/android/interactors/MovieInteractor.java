package jlmd.dev.android.interactors;

import java.util.ArrayList;
import java.util.List;

import jlmd.dev.android.dataaccess.DaoSingleton;
import jlmd.dev.android.dataaccess.Movie;
import jlmd.dev.android.dataaccess.MovieDTO;
import jlmd.dev.android.dataaccess.MovieDao;

public class MovieInteractor {
    DaoSingleton daoSingleton;
    MovieDao movieDao;

    public MovieInteractor(DaoSingleton daoSingleton) {
        this.daoSingleton = daoSingleton;
        this.movieDao = daoSingleton.getDaoSession().getMovieDao();
    }

    public boolean areThereData(){
        return movieDao.loadAll().size() > 0;
    }

    public void saveMovies(List<MovieDTO> movieList){
        for (MovieDTO movie : movieList){
            Movie movieIn =
                    new Movie(movie.getIdDB(),
                            movie.getId(),
                            movie.getTitle(),
                            movie.getOriginal_title(),
                            movie.getOriginal_language(),
                            movie.getOverview(),
                            movie.getPoster_path(),
                            movie.getBackdrop_path(),
                            movie.getRelease_date(),
                            movie.getVote_average(),
                            movie.getPopularity(),
                            movie.getVote_count()
                    );
            movieDao.insert(movieIn);
        }
    }

    public ArrayList<MovieDTO> returnMovies(){
        ArrayList<MovieDTO> moviesBD = new ArrayList<>();
        for (Movie movie : movieDao.loadAll()){
            moviesBD.add(new MovieDTO(
                    movie.getId(),
                    movie.getIdMovie(),
                    movie.getOriginalTitle(),
                    movie.getOriginalLanguage(),
                    movie.getOverview(),
                    movie.getPopularity(),
                    movie.getPosterPath(),
                    movie.getBackdropPath(),
                    movie.getReleaseDate(),
                    movie.getTitle(),
                    movie.getVoteAverage(),
                    movie.getVoteCount()
            ));
        }
        return moviesBD;
    }
}
