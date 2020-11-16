package com.example.m8repasad.ui.Config;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.m8repasad.FragmentInteractionListener;
import com.example.m8repasad.R;

import java.util.Locale;

public class ConfigFragment extends Fragment {

    private ConfigViewModel mViewModel;
    private Spinner langSelector;
    private String lang;
    private Button shaPref;

    public static ConfigFragment newInstance() {
        return new ConfigFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.config_fragment, container, false);
        shaPref = (Button) root.findViewById(R.id.shaPrefBtn);

            shaPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getContext().getSharedPreferences(getString(R.string.savedPreferenceFile), getContext().MODE_PRIVATE);
                sharedPref.edit().clear().commit();
                Toast.makeText(getContext(), getString(R.string.credDeleted), Toast.LENGTH_SHORT).show();
            }
        });

        langSelector = root.findViewById(R.id.langSpin);
        langSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lang = langSelector.getSelectedItem().toString();
                changeLang(getActivity().getResources(), lang);
                //En comptes de fer un shared preference, canvio l'opció al detectar un nou canvi en el spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConfigViewModel.class);
        // TODO: Use the ViewModel
    }

    public void changeLang(Resources res, String locale){
        Configuration configuration = new Configuration(res.getConfiguration());

        switch (locale){
            case "Español":
            case "Spanish":
                configuration.locale = new Locale("es");

                break;
            case "Ingles":
            case "English":
                configuration.locale = new Locale("en");
                break;
        }
        res.updateConfiguration(configuration,res.getDisplayMetrics());
    }

    /*public void refresFrag(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment fragmentConfig = new ConfigFragment();
        transaction.replace(R.id.container, fragmentConfig);
        transaction.commit();
    }*/
}