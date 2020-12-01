package com.example.m8repasad.ui.AddIncidence;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.m8repasad.Incidencia;
import com.example.m8repasad.R;
import com.example.m8repasad.db.IncidenciaDBHelper;

import java.time.Instant;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class AddIncidenceFragment extends Fragment {

    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    private AddIncidenceViewModel mViewModel;
    private Button addInsidenceBtn;
    private EditText editTextTitleInsidence, descEDTXT;
    private Spinner spinnerPriority;

    public static AddIncidenceFragment newInstance() {
        return new AddIncidenceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(AddIncidenceViewModel.class);
        View root = inflater.inflate(R.layout.add_incidence_fragment, container, false);

        addInsidenceBtn = (Button) root.findViewById(R.id.addInsidenceBtn);
        editTextTitleInsidence = (EditText) root.findViewById(R.id.editTextTitleIncidence);
        spinnerPriority = (Spinner)  root.findViewById(R.id.langSpin);
        descEDTXT = (EditText) root.findViewById(R.id.descInput);

        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        addInsidenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int rd = random.nextInt();

                String inc_name = "INC".concat(Integer.toString(rd));

                if(!editTextTitleInsidence.getText().toString().equals(null)){
                    long currdate = Instant.now().getEpochSecond();
                    String curdate = String.valueOf(currdate);
                    String prioridad;

                    if(spinnerPriority.getSelectedItem().toString().equals(getResources().getString(R.string.low))){
                        prioridad = "0";
                    } else if(spinnerPriority.getSelectedItem().toString().equals(getResources().getString(R.string.medium))){
                        prioridad = "1";
                    } else {
                        prioridad = "2";
                    }

                    Incidencia incidencia = new Incidencia(inc_name, editTextTitleInsidence.getText().toString(), prioridad, descEDTXT.getText().toString(), 0, curdate );
                    dbHelper.insertIncidencia(db, incidencia);
                    //singletonIncidencias.getNewInstance().addToListIncidencias(incidencia);

                    Toast.makeText(getContext(), getResources().getString(R.string.incidence)+ " " + inc_name + " " + getResources().getString(R.string.created), Toast.LENGTH_SHORT).show();
                    editTextTitleInsidence.setText("");
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.noTitleMessage), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddIncidenceViewModel.class);
        // TODO: Use the ViewModel
    }

}