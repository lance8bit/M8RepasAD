package com.example.m8repasad.ui.ListIncidence;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m8repasad.FragmentInteractionListener;
import com.example.m8repasad.Incidencia;
import com.example.m8repasad.R;
import com.example.m8repasad.db.IncidenciaDBHelper;
import com.example.m8repasad.ui.Incidencia.IncidenciaFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListIncidenceFragment extends Fragment {

    private ListIncidenceViewModel mViewModel;
    private RecyclerView recyclerView;
    private Spinner spinner;

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
        spinner = root.findViewById(R.id.spinnerFilter);

        //FOR SINGLETON CHECK
        //ArrayList<Incidencia> reto = singletonIncidencias.getNewInstance().getListIncidencias();

        //for(int x = 0; x < reto.size(); x++){
            //Log.i("RECY", "TITLE: " + reto.get(x).getTitleIncidencia());
        //}

        LinearLayoutManager rLManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(rLManager);

        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        MainAdapter rAdapter = new MainAdapter();
                        recyclerView.setAdapter(rAdapter);
                        break;
                    case 1:
                        recyclerView.setAdapter( new MainAdapter(0));
                        break;
                    case 2:
                        recyclerView.setAdapter( new MainAdapter(1));
                        break;
                    case 3:
                        recyclerView.setAdapter( new MainAdapter(2));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return root;
    }

    public class MainAdapter extends RecyclerView.Adapter<ViewHolder>{

        private ArrayList<Incidencia> arrayListIncidencias;
        private Context context;

        public MainAdapter(){
            //arrayListIncidencias = singletonIncidencias.getNewInstance().getListIncidencias();
            arrayListIncidencias = dbHelper.getAllIncidencies();
        }

        public MainAdapter(int status){
            arrayListIncidencias = dbHelper.getFilteredIncidencias(Integer.toString(status));
        }

        private void removeTar(int position){
            dbHelper.deleteIncidencia(arrayListIncidencias.get(position).getNumIncidencia());
            arrayListIncidencias.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeRemoved(position, arrayListIncidencias.size());
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View nVistaHolder = layoutInflater.inflate(R.layout.view_rv_single_incidence, parent, false);
            return new ViewHolder(nVistaHolder);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
            viewHolder.titleInc.setText(arrayListIncidencias.get(position).getTitleIncidencia());

            if(arrayListIncidencias.get(position).getPriorityIncidencia().equals("0")){
                viewHolder.priorityInc.setText(getContext().getResources().getString(R.string.low));
            } else if(arrayListIncidencias.get(position).getPriorityIncidencia().equals("1")){
                viewHolder.priorityInc.setText(getContext().getResources().getString(R.string.medium));
            } else {
                viewHolder.priorityInc.setText(getContext().getResources().getString(R.string.high));
            }

            viewHolder.card_block.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle result = new Bundle();
                    result.putSerializable("incidencia", arrayListIncidencias.get(position));

                    IncidenciaFragment incidenciaFragment = new IncidenciaFragment();
                    FragmentInteractionListener fragmentInteractionListener = (FragmentInteractionListener)getActivity();
                    incidenciaFragment.setArguments(result);
                    fragmentInteractionListener.changeFragment(incidenciaFragment);
                }
            });

            viewHolder.clButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(getResources().getString(R.string.warningMessage));
                    builder.setMessage(getResources().getString(R.string.infoMessageOne));
                    builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //singletonIncidencias.getNewInstance().removeEntries();
                            removeTar(position);
                            Toast.makeText(getContext(), getResources().getString(R.string.successDeleteIncidence), Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton(getResources().getString(R.string.no), null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            switch (arrayListIncidencias.get(position).getStaIncidencia()){
                case 0:
                    viewHolder.imgPrior.setImageResource(R.drawable.rounded_red_button);
                    break;
                case 1:
                    viewHolder.imgPrior.setImageResource(R.drawable.rounded_orange_button);
                    break;
                case 2:
                    viewHolder.imgPrior.setImageResource(R.drawable.rounded_green_button);
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return arrayListIncidencias.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleInc, priorityInc;
        public CardView card_block;
        public Button clButton;
        public ImageView imgPrior;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleInc = (TextView) itemView.findViewById(R.id.titleIncidence);
            priorityInc = (TextView) itemView.findViewById(R.id.priorityIncidence);
            card_block = (CardView) itemView.findViewById(R.id.cardview_cont);
            clButton = (Button) itemView.findViewById(R.id.buttonClear);
            imgPrior = (ImageView) itemView.findViewById(R.id.colorPrior);
        }

        @Override
        public void onClick(View view){

        }
    }

}