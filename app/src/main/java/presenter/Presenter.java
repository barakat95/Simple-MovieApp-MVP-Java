package presenter;

import java.util.List;

import model.Movie;
import model.OnDataListener;
import view.MainActivity;

public class Presenter implements IPresenter {
    MainActivity mainActivity;
    Movie movie;

    public Presenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        movie = new Movie();
        mainActivity.showProgressBar();
    }

    @Override
    public void getDataFromModel() {
        movie.getMovies(new OnDataListener() {
            @Override
            public void onSuccess(List<Movie> movieList) {
                mainActivity.hideProgressBar();
                mainActivity.updateMovies(movieList);
            }

            @Override
            public void onFailure(String msgFailure) {
                mainActivity.hideProgressBar();
                mainActivity.updateMoviesError(msgFailure);
            }
        });
    }
}
