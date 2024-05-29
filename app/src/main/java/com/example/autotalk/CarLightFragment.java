package com.example.autotalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarLightFragment extends Fragment {

    private PercentageAdapter percentageAdapter;
    private List<String> percentages;
    private RecyclerView recyclerView;
    private Switch toggleSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_light, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        percentages = new ArrayList<>(Arrays.asList("Light ID:1", "Light ID:2", "Light ID:3", "Light ID:4"));
        percentageAdapter = new PercentageAdapter(percentages);
        recyclerView.setAdapter(percentageAdapter);


        return view;
    }
}