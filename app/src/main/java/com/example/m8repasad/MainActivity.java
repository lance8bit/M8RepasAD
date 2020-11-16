package com.example.m8repasad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEdTx, passwordEdTx;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEdTx = (EditText) findViewById(R.id.usernameEdTxt);
        passwordEdTx = (EditText) findViewById(R.id.passwordEdTxt);
        login_btn = (Button) findViewById(R.id.loginBtn);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.savedPreferenceFile), getBaseContext().MODE_PRIVATE);
        String shUsername = sharedPref.getString("username", "");
        String shPass = sharedPref.getString("password", "");

        usernameEdTx.setText(shUsername);
        passwordEdTx.setText(shPass);

        final Usuaris user1 = new Usuaris("admin", "admin");

        if(shUsername.equals(user1.getUsername()) &&  shPass.equals(user1.getPassword()) ){
            Toast.makeText(MainActivity.this, R.string.loginShared, Toast.LENGTH_SHORT).show();
            Intent menuOpcions = new Intent(getApplicationContext(), fragmentContainer.class);
            startActivity(menuOpcions);
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userInput = usernameEdTx.getText().toString();
                String passInput = passwordEdTx.getText().toString();

                Log.i("userInp", userInput);
                Log.i("passInp", passInput);

                if (checkUser(passInput,user1.getPassword(), userInput, user1.getUsername()) == false){
                    Toast.makeText(MainActivity.this, R.string.badLogin, Toast.LENGTH_SHORT).show();
                } else {
                    Intent menuOpcions = new Intent(getApplicationContext(), fragmentContainer.class);
                    Toast.makeText(MainActivity.this, R.string.successLogin, Toast.LENGTH_SHORT).show();
                    Context context = getApplicationContext();
                    SharedPreferences sharedPref = context.getSharedPreferences(
                            getString(R.string.savedPreferenceFile), context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", usernameEdTx.getText().toString());
                    editor.putString("password", passwordEdTx.getText().toString());
                    editor.commit();
                    startActivity(menuOpcions);
                }
            }
        });

    }

    public boolean checkUser(String passInp, String passUsr, String userInp, String userUsr){
        boolean result = false;
        if(userInp.equals(userUsr) && passInp.equals(passUsr)){
            result = true;
        }
        return result;
    }
}
