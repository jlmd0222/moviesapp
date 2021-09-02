package jlmd.dev.android.dataaccess;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDTO implements Parcelable {
    public Long idDB;
    public Long id;
    public String original_title;
    public String original_language;
    public String overview;
    public double popularity;
    public String poster_path;
    public String backdrop_path;
    public String release_date;
    public String title;
    public double vote_average;
    public Long vote_count;

    public MovieDTO(Long idDB, Long id, String original_title, String original_language, String overview, double popularity, String poster_path, String backdrop_path, String release_date, String title, double vote_average, Long vote_count) {
        this.idDB = idDB;
        this.id = id;
        this.original_title = original_title;
        this.original_language = original_language;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDB() {
        return idDB;
    }

    public void setIdDB(Long idDB) {
        this.idDB = idDB;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public Long getVote_count() {
        return vote_count;
    }

    public void setVote_count(Long vote_count) {
        this.vote_count = vote_count;
    }

    protected MovieDTO(Parcel in) {
        id = in.readLong();
        idDB = in.readLong();
        original_title = in.readString();
        original_language = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        backdrop_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        vote_average = in.readDouble();
        vote_count = in.readLong();
    }

    public static final Creator<MovieDTO> CREATOR = new Creator<MovieDTO>() {
        @Override
        public MovieDTO createFromParcel(Parcel in) {
            return new MovieDTO(in);
        }

        @Override
        public MovieDTO[] newArray(int size) {
            return new MovieDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(idDB);
        dest.writeString(original_title);
        dest.writeString(original_language);
        dest.writeString(overview);
        dest.writeDouble(popularity);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeDouble(vote_average);
        dest.writeLong(vote_count);
    }

    public static class MoviesNode{
        @SerializedName("page")
        @Expose
        int page;
        @SerializedName("results")
        @Expose
        List<MovieDTO> results;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<MovieDTO> getResults() {
            return results;
        }

        public void setResults(List<MovieDTO> results) {
            this.results = results;
        }
    }
}
