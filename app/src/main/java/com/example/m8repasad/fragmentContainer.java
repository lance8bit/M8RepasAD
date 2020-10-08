package com.example.m8repasad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.example.m8repasad.ui.Menu.MenuFragment;

public class fragmentContainer extends AppCompatActivity implements FragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MenuFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void changeFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //  If I need to send args to fragment:
        //  Bundle args = new Bundle();
        //  args.putInt(AddIncidenceFragment.ARG_POSITION, position);
        //  addIncidenceFragment.setArguments(args);

        fragmentTransaction.replace(R.id.container, fragment);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}