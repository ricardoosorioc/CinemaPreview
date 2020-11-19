package com.example.cinema.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cinema.Adapters.FilmAdapter;
import com.example.cinema.BD.Repository;
import com.example.cinema.Model.Film;
import com.example.cinema.Presenter.HomePresenter;
import com.example.cinema.R;

import java.util.List;


public class HomeFragment extends Fragment {

    private HomePresenter homeActivityPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeActivityPresenter = new HomePresenter();

        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        final Fragment pointerSaver = this;

        //Add recyclerView
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        final FilmAdapter adapter = new FilmAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setFilmlist(Repository.getHardcodedList());

        //Agrega spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.filter_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGenre = (String)parent.getItemAtPosition(position);
                List<Film> filteredFilmlist = homeActivityPresenter.filterByGenre(selectedGenre);
                final FilmAdapter adapter = new FilmAdapter(pointerSaver);
                recyclerView.setAdapter(adapter);
                adapter.setFilmlist(filteredFilmlist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return view;
    }
}