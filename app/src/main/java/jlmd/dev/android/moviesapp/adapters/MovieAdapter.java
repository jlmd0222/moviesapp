package jlmd.dev.android.moviesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jlmd.dev.android.dataaccess.MovieDTO;
import jlmd.dev.android.moviesapp.R;
import jlmd.dev.android.services.Constants;

public class MovieAdapter extends RecyclerView.Adapter implements Filterable {
    private Context context;
    private List<MovieDTO> movieList;
    private List<MovieDTO> movieListAll;
    private OnMovieListener mOnMovieListener;

    public MovieAdapter(Context context, List<MovieDTO> movieList, OnMovieListener mOnMovieListener) {
        this.context = context;
        this.movieList = movieList;
        this.movieListAll = new ArrayList<>(this.movieList);
        this.mOnMovieListener = mOnMovieListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder;
        View view;

        if (i == R.layout.empty_view){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.empty_view, viewGroup, false);
            holder = new EmptyViewViewHolder(view);
        } else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
            holder = new ViewHolderMovies(view, mOnMovieListener);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) != R.layout.empty_view) {
            final MovieDTO movie = movieList.get(i);
            MovieAdapter.ViewHolderMovies viewHolderMovies = (MovieAdapter.ViewHolderMovies) viewHolder;

            viewHolderMovies.textViewTitle.setText(movie.getTitle());
            viewHolderMovies.textViewOverview.setText(movie.getOverview());
            Picasso.get()
                    .load(Constants.URL_BASE_IMG + movie.poster_path)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(viewHolderMovies.imageViewMovie);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (movieList.size() == 0){
            return R.layout.empty_view;
        }
        else return R.layout.item_movie;
    }

    @Override
    public int getItemCount() {
        if (movieList == null || movieList.isEmpty()) {
            return 1;
        } else {
            return movieList.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolderMovies extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageViewMovie;
        TextView textViewTitle,textViewOverview;
        OnMovieListener onMovieListener;
        CardView cardViewMovie;

        public ViewHolderMovies (View itemView, OnMovieListener onMovieListener) {
            super(itemView);
            imageViewMovie = itemView.findViewById(R.id.ivMovieImage);
            textViewTitle = itemView.findViewById(R.id.tvMovieName);
            textViewOverview = itemView.findViewById(R.id.tvMovieDescription);
            cardViewMovie = itemView.findViewById(R.id.cvMovie);
            this.onMovieListener = onMovieListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMovieListener.OnMovieClick(movieList.get(getAdapterPosition()));
        }
    }

    public static class EmptyViewViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnMovieListener {
        void OnMovieClick(MovieDTO movieDTO);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MovieDTO> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(movieListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MovieDTO movie : movieListAll) {
                    if (movie.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(movie);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            movieList.clear();
            movieList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
