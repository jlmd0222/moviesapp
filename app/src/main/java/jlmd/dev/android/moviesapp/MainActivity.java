package jlmd.dev.android.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import jlmd.dev.android.dataaccess.DaoSingleton;
import jlmd.dev.android.dataaccess.MovieDTO;
import jlmd.dev.android.interactors.MovieInteractor;
import jlmd.dev.android.moviesapp.adapters.MovieAdapter;
import jlmd.dev.android.services.ApiError;
import jlmd.dev.android.services.Constants;
import jlmd.dev.android.services.ErrorUtils;
import jlmd.dev.android.services.RestApiAdapter;
import jlmd.dev.android.services.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieListener{
    private EditText editTextSearch;
    private RecyclerView recyclerViewSearchResults;
    private MovieAdapter movieAdapter;
    private MovieInteractor movieInteractor;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponentsView();
        initEventsView();

        DaoSingleton daoSingleton = DaoSingleton.getInstance(this);
        movieInteractor = new MovieInteractor(daoSingleton);

        if (movieInteractor.areThereData())
            returnMovies();
        else
            consultMovies();
    }

    private void initComponentsView() {
        editTextSearch = findViewById(R.id.editTextSearch);
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerViewSearchResults.setLayoutManager(linearLayoutManager);
        recyclerViewSearchResults.setHasFixedSize(true);
    }

    private void initEventsView() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                movieAdapter.getFilter().filter(editable.toString());
            }
        });
    }

    @Override
    public void OnMovieClick(MovieDTO movieDTO) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra("movie", (Parcelable) movieDTO);
        startActivity(intent);
    }

    /**Consulta el listado de peliculas por medio el endpoint**/
    private void consultMovies() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando peliculas");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RestApiAdapter restApiAdapter=new RestApiAdapter();
        Service service=restApiAdapter.getClientService();
        service.getDataMovies(Constants.API_KEY).enqueue(new Callback<MovieDTO.MoviesNode>() {
            @Override
            public void onResponse(Call<MovieDTO.MoviesNode> call, Response<MovieDTO.MoviesNode> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    assert response.body() != null;
                    List<MovieDTO> userList = response.body().getResults();
                    if(userList.size() > 0) {
                        movieInteractor.saveMovies(userList);
                        returnMovies();
                    } else{
                        recyclerViewSearchResults.setAdapter(null);
                    }
                }else {
                    progressDialog.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    showMessageError(apiError.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MovieDTO.MoviesNode> call, Throwable t) {
                System.out.println(t.getMessage());
                showMessageError(t.getMessage());
            }
        });
    }

    /**Retorna el listado de peliculas almacenados localmente**/
    private void returnMovies() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando peliculas");
        progressDialog.setCancelable(false);
        progressDialog.show();
        List<MovieDTO> movieList = movieInteractor.returnMovies();
        movieAdapter = new MovieAdapter(getApplicationContext(), movieList, this);
        recyclerViewSearchResults.setAdapter(movieAdapter);
        progressDialog.dismiss();
    }

    private void showMessageError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}