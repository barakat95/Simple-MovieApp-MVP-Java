package view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mohammed.movieappmvp.R;

import java.util.List;

import model.Movie;
import presenter.Presenter;

public class MainActivity extends AppCompatActivity implements IMain {

    ProgressBar progressBar;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        presenter = new Presenter(this);
        presenter.getDataFromModel();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateMovies(List<Movie> moviesList) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieAdapter(moviesList, R.layout.row_layout, getApplicationContext()));

    }

    @Override
    public void updateMoviesError(String msgError) {
        Toast.makeText(this, msgError, Toast.LENGTH_LONG).show();
    }
}
