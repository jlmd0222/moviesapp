package jlmd.dev.android.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jlmd.dev.android.dataaccess.MovieDTO;
import jlmd.dev.android.services.Constants;

public class MovieActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private TextView textViewOverview;
    private TextView textViewReleaseDate;
    private TextView textViewVote;
    private TextView textViewPopularity;
    private ImageView imageViewMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initComponentsView();

        if (getIntent().getExtras() != null){
            MovieDTO movie = getIntent().getExtras().getParcelable("movie");
            if (movie != null) {
                Picasso.get()
                        .load(Constants.URL_BASE_IMG + movie.backdrop_path)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imageViewMovie);
                textViewTitle.setText(movie.getTitle());
                textViewOverview.setText(movie.getOverview());
                textViewReleaseDate.setText(movie.getRelease_date());
                textViewVote.setText(String.valueOf(movie.getVote_average()));
                textViewPopularity.setText(String.valueOf(movie.getPopularity()));
            }
        }
    }

    private void initComponentsView() {
        imageViewMovie = findViewById(R.id.ivImageDetail);
        textViewTitle = findViewById(R.id.tvTitleDetail);
        textViewOverview = findViewById(R.id.tvOverviewDetail);
        textViewReleaseDate = findViewById(R.id.tvReleaseDateDetail);
        textViewVote = findViewById(R.id.tvVoteDetail);
        textViewPopularity = findViewById(R.id.tvPopularityDetail);
    }
}