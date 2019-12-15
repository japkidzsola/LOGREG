package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInActivity extends AppCompatActivity {

    TextView teljesnev;
    Button kijelentkezes;
    EditText bejelentkezofelhasz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        init();

        AdatbazisSegito dbhelper = new AdatbazisSegito(LoggedInActivity.this);
        Cursor cursorAdatok = dbhelper.Bejelentkezes();
        String  bejelentkezes = dbhelper.Bejelentk();

        //String bejelentkezoneveh = bejelentkezofelhasz.getText().toString();
        StringBuffer stringBuffer = new StringBuffer();
        while (cursorAdatok.moveToNext()){
            if (bejelentkezes.equals(cursorAdatok.getString(1)))
            {
                stringBuffer.append("Üdvözlünk: "+cursorAdatok.getString(4));
            }
            else if (bejelentkezes.equals(cursorAdatok.getString(2)))
            {
                stringBuffer.append("Üdvözlünk: "+cursorAdatok.getString(4));
            }
            else
            {

            }
        }
        teljesnev.setText(stringBuffer.toString());

        kijelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regisztracioActivityre =
                        new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(regisztracioActivityre);
                finish();
            }
        });

    }

    public void init()
    {
        teljesnev = findViewById(R.id.teljesnev);
        kijelentkezes = findViewById(R.id.kijelentkezes);
        bejelentkezofelhasz = findViewById(R.id.bejelentkezofelhasz);
    }

}
