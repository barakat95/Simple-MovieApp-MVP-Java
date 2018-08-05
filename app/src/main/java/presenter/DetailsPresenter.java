package presenter;

import java.util.List;

import model.Movie;
import model.OnDataListener;
import view.MovieDetails;

public class DetailsPresenter implements IPresenter {
    MovieDetails movieDetails;
    Movie movie;

    public DetailsPresenter(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
        movie = new Movie();
        movieDetails.showProgressBar();
    }

    @Override
    public void getDataFromModel() {
        movie.getMovies(new OnDataListener() {
            @Override
            public void onSuccess(List<Movie> movieList) {
                movieDetails.hideProgressBar();
                movieDetails.updateMovies(movieList);
            }

            @Override
            public void onFailure(String msgFailure) {
                movieDetails.hideProgressBar();
                movieDetails.updateMoviesError(msgFailure);
            }
        });
    }
}
