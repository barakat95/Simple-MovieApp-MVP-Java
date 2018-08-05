package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mohammed.movieappmvp.R;

import java.util.List;

import model.Movie;
import presenter.DetailsPresenter;
import presenter.Presenter;

public class MovieDetails extends AppCompatActivity implements IMain {

    private static final String TAG = "GalleryActivity";
    ProgressBar progressBar;
    DetailsPresenter detailsPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        progressBar = findViewById(R.id.progress_bar_details);
        detailsPresenter = new DetailsPresenter(this);
        detailsPresenter.getDataFromModel();
        getIncomingIntent();

    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if (getIntent().hasExtra("movieTitle")
                && getIntent().hasExtra("movieDate")
                && getIntent().hasExtra("movieOverview")
                && getIntent().hasExtra("posterImage")) {
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String movieTitle = getIntent().getStringExtra("movieTitle");

            String movieDate = getIntent().getStringExtra("movieDate");

            String movieOverview = getIntent().getStringExtra("movieOverview");

            String posterImage = getIntent().getStringExtra("posterImage");

            setDetails(posterImage, movieTitle, movieDate, movieOverview);
        }
    }


    private void setDetails(String imageUrl, String movieTitle, String movieDate, String movieOverview) {
        Log.d(TAG, "setImage: setting te image and name to widgets.");

        TextView title = findViewById(R.id.movie_title_details);
        title.setText(movieTitle);

        TextView date = findViewById(R.id.movie_date_details);
        date.setText(movieDate);

        TextView overview = findViewById(R.id.movie_overview_details);
        overview.setText(movieOverview);


        ImageView image = findViewById(R.id.movie_poster_details);
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w185//" + imageUrl)
                .into(image);

//        Glide.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w185//" + movies.get().getPosterPath().toString()).into(image);

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

    }

    @Override
    public void updateMoviesError(String msgError) {
        Toast.makeText(this, "" + msgError.toString(), Toast.LENGTH_SHORT).show();
    }
}


