package com.example.foodapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.Adapters.Categoris_Adapter;
import com.example.foodapp.Adapters.All_Modals.Categoris_Modal;
import com.example.foodapp.R;

import java.util.ArrayList;

public class Home_Fragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public Home_Fragment() {

    }

    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    LinearLayoutManager linearLayoutManager;
    ArrayList<Categoris_Modal> c_list;
    Categoris_Adapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_, container, false);
        recyclerView = view.findViewById(R.id.categoris_recyclerview_id);
        linearLayoutManager = new LinearLayoutManager(view.getContext(), linearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        c_list = new ArrayList<>();

        c_list.add(new Categoris_Modal(R.drawable.c_pizza,"Pizza"));
        c_list.add(new Categoris_Modal(R.drawable.burger,"Burger"));
        c_list.add(new Categoris_Modal(R.drawable.desserts,"Dessert"));
        c_list.add(new Categoris_Modal(R.drawable.drinks,"Drinks"));
        c_list.add(new Categoris_Modal(R.drawable.snacks,"Snacks"));

        adapter = new Categoris_Adapter(c_list);

        recyclerView.setAdapter(adapter);

        return view;
    }
}