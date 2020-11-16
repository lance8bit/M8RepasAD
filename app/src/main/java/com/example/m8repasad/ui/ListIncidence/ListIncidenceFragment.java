package com.example.m8repasad.ui.ListIncidence;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.m8repasad.Incidencia;
import com.example.m8repasad.R;
import com.example.m8repasad.db.IncidenciaDBHelper;
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

    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    public static ListIncidenceFragment newInstance() {
        return new ListIncidenceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ListIncidenceViewModel.class);
        View root = inflater.inflate(R.layout.list_incidence_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerIncidences);

        //FOR SINGLETON CHECK
        //ArrayList<Incidencia> reto = singletonIncidencias.getNewInstance().getListIncidencias();

        //for(int x = 0; x < reto.size(); x++){
            //Log.i("RECY", "TITLE: " + reto.get(x).getTitleIncidencia());
        //}

        LinearLayoutManager rLManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(rLManager);

        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        MainAdapter rAdapter = new MainAdapter();
        recyclerView.setAdapter(rAdapter);

        return root;
    }

    public class MainAdapter extends RecyclerView.Adapter<ViewHolder>{

        private ArrayList<Incidencia> arrayListIncidencias;

        public MainAdapter(){
            //arrayListIncidencias = singletonIncidencias.getNewInstance().getListIncidencias();
            arrayListIncidencias = dbHelper.getAllIncidencies();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View nVistaHolder = layoutInflater.inflate(R.layout.view_rv_single_incidence, parent, false);
            return new ViewHolder(nVistaHolder);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            viewHolder.titleInc.setText(arrayListIncidencias.get(position).getTitleIncidencia());
            viewHolder.priorityInc.setText(arrayListIncidencias.get(position).getPriorityIncidencia());
        }

        @Override
        public int getItemCount() {
            return arrayListIncidencias.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleInc, priorityInc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleInc = (TextView) itemView.findViewById(R.id.titleIncidence);
            priorityInc = (TextView) itemView.findViewById(R.id.priorityIncidence);
        }
    }

}