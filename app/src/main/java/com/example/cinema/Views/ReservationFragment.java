package com.example.cinema.Views;

import android.os.Build;
import android.os.Bundle;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.RequiresApi;

import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.cinema.R;
import com.example.cinema.Adapters.ReservationAdapter;
import com.example.cinema.Controlers.SwipeToDelete;
import com.example.cinema.BD.Repository;

import java.io.Serializable;

public class ReservationFragment extends Fragment implements Serializable {



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reservation, container, false);

        //adding recycler view to fragment
        RecyclerView recyclerView = view.findViewById(R.id.reservation_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        ReservationAdapter rAdapter = new ReservationAdapter(this);
        rAdapter.setReservationList(Repository.getReservationList());

        recyclerView.setAdapter(rAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDelete(rAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        if (Repository.getReservationList().isEmpty()) {
            final TextView header = view.findViewById(R.id.no_reservations_message_header);
            header.setVisibility(View.VISIBLE);
            final TextView body = view.findViewById(R.id.no_reservations_message_body);
            body.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
