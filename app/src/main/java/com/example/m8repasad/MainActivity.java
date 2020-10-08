package com.example.m8repasad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = usernameEdTx.getText().toString();
                String passInput = passwordEdTx.getText().toString();
                Usuaris user1 = new Usuaris("steve", "1234");
                Log.i("userInp", userInput);
                Log.i("passInp", passInput);

                if (checkUser(passInput,user1.getPassword(), userInput, user1.getUsername()) == false){
                    Toast.makeText(MainActivity.this, R.string.badLogin, Toast.LENGTH_SHORT).show();
                } else {
                    Intent menuOpcions = new Intent(getApplicationContext(), fragmentContainer.class);
                    Toast.makeText(MainActivity.this, R.string.successLogin, Toast.LENGTH_SHORT).show();
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
