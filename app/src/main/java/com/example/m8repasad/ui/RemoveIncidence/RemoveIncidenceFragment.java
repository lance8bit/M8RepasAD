package com.example.m8repasad.ui.RemoveIncidence;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m8repasad.R;

public class RemoveIncidenceFragment extends Fragment {

    private RemoveIncidenceViewModel mViewModel;

    public static RemoveIncidenceFragment newInstance() {
        return new RemoveIncidenceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.remove_incidence_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RemoveIncidenceViewModel.class);
        // TODO: Use the ViewModel
    }

}