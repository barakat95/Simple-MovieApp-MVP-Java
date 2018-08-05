package view;

import java.util.List;

import model.Movie;

public interface IMain {
    void showProgressBar();

    void hideProgressBar();

    void updateMovies(List<Movie> moviesList);

    void updateMoviesError(String msgError);
}
