package com.example.m8repasad.ui.ListIncidence;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.m8repasad.Incidencia;
import com.example.m8repasad.R;
import com.example.m8repasad.singletonIncidencias;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListIncidenceFragment extends Fragment {

    private ListIncidenceViewModel mViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    public static ListIncidenceFragment newInstance() {
        return new ListIncidenceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ListIncidenceViewModel.class);
        View root = inflater.inflate(R.layout.list_incidence_fragment, container, false);

        ArrayList<Incidencia> reto = singletonIncidencias.getNewInstance().getListIncidencias();

        for(int x = 0; x < reto.size(); x++){
            Log.i("RECY", "TITLE: " + reto.get(x).getTitleIncidencia());
        }

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerIncidences);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListIncidenceViewModel.class);
        // TODO: Use the ViewModel
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.MyViewHolder> {

        private String[] mDataset;

        public static class MyViewHolder extends RecyclerView.ViewHolder{

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.MyViewHolder holder, int position) {

        }


        @Override
        public int getItemCount() {
            return 0;
        }
    }


}