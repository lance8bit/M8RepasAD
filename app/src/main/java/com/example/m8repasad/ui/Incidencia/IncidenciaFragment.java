package com.example.m8repasad.ui.Incidencia;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.m8repasad.Incidencia;
import com.example.m8repasad.R;
import com.example.m8repasad.db.IncidenciaDBHelper;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class IncidenciaFragment extends Fragment implements View.OnClickListener {

    private IncidenciaViewModel mViewModel;
    private TextView titleResult, statusResult, descriptionResult, dateResult, priorityResult;
    private Button staBtn;

    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    private String incidencia_id;

    public static IncidenciaFragment newInstance() {
        return new IncidenciaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_incidencia, container, false);

        Bundle bundle = getArguments();
        final Incidencia incidencia = (Incidencia) bundle.getSerializable("incidencia");
        incidencia_id = incidencia.getNumIncidencia();

        titleResult = (TextView) root.findViewById(R.id.resTxtInc);
        statusResult = (TextView) root.findViewById(R.id.resStatus);
        descriptionResult = (TextView) root.findViewById(R.id.resDesc);
        dateResult = (TextView) root.findViewById(R.id.resDate);

        priorityResult = (TextView) root.findViewById(R.id.resPriority);
        staBtn = (Button) root.findViewById(R.id.buttonStatus);

        titleResult.setText(incidencia.getTitleIncidencia());
        descriptionResult.setText(incidencia.getDescIncidencia());

        long date = Long.parseLong(incidencia.getDateIncidencia());
        Instant instant = Instant.ofEpochSecond(date);

        Date myDate = Date.from(instant);
        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = smp.format(myDate);

        dateResult.setText(formattedDate);

        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        changeButton(incidencia);

        staBtn.setOnClickListener(this);

        switch (incidencia.getPriorityIncidencia()){
            case "0":
                priorityResult.setText(getResources().getString(R.string.low));
                break;
            case "1":
                priorityResult.setText(getResources().getString(R.string.medium));
                break;
            case "2":
                priorityResult.setText(getResources().getString(R.string.high));
                break;
        }
        priorityResult.setText(incidencia.getPriorityIncidencia());

        return root;
    }

    @Override
    public void onClick(View view){
        Incidencia in_db = dbHelper.getIncidencia(incidencia_id);

        int status = in_db.getStaIncidencia();
        switch (status){
            case 0:
                dbHelper.changeStatus(db, incidencia_id, status + 1);
                statusResult.setText(R.string.assigned);
                staBtn.setBackgroundResource(R.drawable.rounded_orange_button);
                break;
            case 1:
                dbHelper.changeStatus(db, incidencia_id, status + 1);
                statusResult.setText(R.string.solved);
                staBtn.setBackgroundResource(R.drawable.rounded_green_button);
                break;
            case 2:
                break;
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(IncidenciaViewModel.class);
        // TODO: Use the ViewModel
    }

    public void changeButton(Incidencia incidencia){

        int num = incidencia.getStaIncidencia();
        switch (num){
            case 0:
                statusResult.setText(R.string.pending);
                staBtn.setBackgroundResource(R.drawable.rounded_red_button);
                break;
            case 1:
                statusResult.setText(R.string.assigned);
                staBtn.setBackgroundResource(R.drawable.rounded_orange_button);
                break;
            case 2:
                statusResult.setText(R.string.solved);
                staBtn.setBackgroundResource(R.drawable.rounded_green_button);
                break;
        }
    }

}