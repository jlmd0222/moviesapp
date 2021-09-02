package jlmd.dev.android.dataaccess;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(active = true)
public class Movie {
    @Id
    private Long id;
    private Long idMovie;

    @NotNull
    private String title;
    private String original_title;
    private String original_language;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private double vote_average;
    private double popularity;
    private Long vote_count;

    @Generated
    private transient DaoSession daoSession;

    @Generated
    private transient MovieDao myDao;

    @Generated
    private transient Long user__resolvedKey;

    @Generated
    public Movie() {
    }

    public Movie(Long id) {
        this.id = id;
    }

    @Generated
    public Movie(Long id, Long idMovie, String title, String original_title, String original_language, String overview, String poster_path, String backdrop_path, String release_date, double vote_average, double popularity, Long vote_count) {
        this.id = id;
        this.idMovie = idMovie;
        this.title = title;
        this.original_title = original_title;
        this.original_language = original_language;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.popularity = popularity;
        this.vote_count = vote_count;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMovieDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    @NotNull
    public String getOriginalTitle() {
        return original_title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setOriginalTitle(@NotNull String original_title) {
        this.original_title = original_title;
    }

    @NotNull
    public String getOriginalLanguage() {
        return original_language;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setOriginalLanguage(@NotNull String original_language) {
        this.original_language = original_language;
    }

    @NotNull
    public String getOverview() {
        return overview;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setOverview(@NotNull String overview) {
        this.overview = overview;
    }

    @NotNull
    public String getPosterPath() {
        return poster_path;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPosterPath(@NotNull String poster_path) {
        this.poster_path = poster_path;
    }

    @NotNull
    public String getBackdropPath() {
        return backdrop_path;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setBackdropPath(@NotNull String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @NotNull
    public String getReleaseDate() {
        return release_date;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setReleaseDate(@NotNull String release_date) {
        this.release_date = release_date;
    }

    @NotNull
    public Double getVoteAverage() {
        return vote_average;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setVoteAverage(@NotNull Double vote_average) {
        this.vote_average = vote_average;
    }

    @NotNull
    public Double getPopularity() {
        return popularity;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setPopularity(@NotNull Double popularity) {
        this.popularity = popularity;
    }

    public Long getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(Long vote_count) {
        this.vote_count = vote_count;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }
}
