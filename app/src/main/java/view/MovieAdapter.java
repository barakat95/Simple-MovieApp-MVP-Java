package view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mohammed.movieappmvp.R;

import java.util.List;

import model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private int rowLayout;
    public Context context;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        ImageView moviePoster;


        public MovieViewHolder(View v) {
            super(v);
//            v.setOnClickListener(this);
            movieTitle = v.findViewById(R.id.title);
            data = v.findViewById(R.id.subtitle);
            movieDescription = v.findViewById(R.id.description);
            moviePoster = v.findViewById(R.id.movie_poster);
            layout = v.findViewById(R.id.linear_layout);
        }

//        @Override
//        public void onClick(View view) {
//
//            Toast.makeText(view.getContext(), "Clicked Item : " + movieTitle.getText().toString(), Toast.LENGTH_SHORT).show();
//        }
    }

    public MovieAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        Glide.with(context).load("http://image.tmdb.org/t/p/w185//" + movies.get(position).getPosterPath().toString()).into(holder.moviePoster);


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MovieDetails.class);
                intent.putExtra("movieTitle", movies.get(position).getTitle());
                intent.putExtra("movieDate", movies.get(position).getReleaseDate());
                intent.putExtra("movieOverview", movies.get(position).getOverview());
                intent.putExtra("posterImage", movies.get(position).getPosterPath());
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
