package model;

import java.util.List;

public interface OnDataListener {
    void onSuccess(List<Movie> movieList);

    void onFailure(String msgFailure);
}
