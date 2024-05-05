package com.example.lab4_20185910.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab4_20185910.R;
import com.example.lab4_20185910.databinding.FragmentClimaBinding;
import com.example.lab4_20185910.databinding.FragmentGeolocalizacionBinding;


public class GeolocalizacionFragment extends Fragment {

    FragmentGeolocalizacionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGeolocalizacionBinding.inflate(inflater,container,false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }


}