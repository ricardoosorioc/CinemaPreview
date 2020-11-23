package com.example.cinema.Views;



import android.content.Intent;
import android.os.Build;
//import android.support.annotation.RequiresApi;
//import android.support.v4.app.NavUtils;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinema.BD.Repository;
import com.example.cinema.R;
import com.example.cinema.Controlers.SwipeButton;
import com.example.cinema.Model.Film;
import com.example.cinema.Adapters.GridViewAdapter;
//import com.example.cinema.presenter.MakeReservationPresenter;
//import com.example.cinema.repository.Repository;

public class MainReservationActivity extends AppCompatActivity  {
    //private MakeReservationPresenter presenter;
    private GridViewAdapter adapter;

    private SwipeButton swipeButton;
    private SwipeButton.OnSwipeButtonListener swipeButtonExpandedListener = new SwipeButton.OnSwipeButtonListener() {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onSwipeButtonExpanded(View v) {
            //saveReservation();
            //don't allow to return to this activity
            MainReservationActivity.this.finish();
        }

        @Override
        public void onSwipeButtonMoved(View v) {
            if (adapter.getSelectedPositions().size() > 0) {
                swipeButton.setEnabled(true);
            } else {
                //swipeButton shouldnt expand
                swipeButton.setEnabled(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reservation);

        //retrieve values from Intent
        Intent creatorIntent = getIntent();
        Film film = (Film)creatorIntent.getSerializableExtra("film");
        String time = creatorIntent.getStringExtra("time");

        getSupportActionBar().setTitle("Reserve");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //create the presenter and update the with the new film information on View
        /*presenter = new MakeReservationPresenter(this, film, time);
        presenter.updatePoster();
        presenter.updateTitle();
        presenter.updateStartTime();*/

        GridView gridView = (GridView)findViewById(R.id.gridPlaces);
        adapter = new GridViewAdapter(this);
        adapter.setCinemaPlacesState(Repository.getCinemaPlaces(film.getTitle(), time));
        gridView.setAdapter(adapter);

        //Attach swipe listener to SwipeButton
        swipeButton = findViewById(R.id.swipe_btn);
        swipeButton.setSwipeListener(swipeButtonExpandedListener);
    }

    /*
    @Override
    public void setPoster(int moviePosterID) {
        ImageView posterImageView = (ImageView)findViewById(R.id.moviePosterImageView);
        posterImageView.setImageResource(moviePosterID);
    }

    @Override
    public void setMovieTitle(String title) {
        TextView titleTextView = (TextView)findViewById(R.id.movieTitleTextView);
        titleTextView.setText(title);
    }

    @Override
    public void setStartTime(String time) {
        TextView timeTextView = (TextView)findViewById(R.id.movieHourTextView);
        timeTextView.setText(time);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void saveReservation() {
        presenter.setListPlaces(adapter.getSelectedPositions());
        presenter.createReservation();

        //return to Parent
        Intent parentIntent = NavUtils.getParentActivityIntent(this);
        startActivity(parentIntent);
    }*/
}