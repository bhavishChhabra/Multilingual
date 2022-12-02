package com.example.multilanguage;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources; 
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    String currentLanguage = "en", currentLang;

    Button l, c;
    EditText u, p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        loadLocale();
        l = findViewById(R.id.login);
        c = findViewById(R.id.change);
        u = findViewById(R.id.user);
        p = findViewById(R.id.pass);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLang();
            }
        });
    }

    private void ChangeLang() {
        final String lang[] = {"English", "Hindi"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Change Language");
        mBuilder.setSingleChoiceItems(lang, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setlocale("");
                    recreate();
                }
                else if (i == 1) {
                    setlocale("hi");
                    recreate();
                }
            }
        });

        mBuilder.create();
        mBuilder.show();
    }

//    private void setlocale(String Languages) {
//        Locale locale= new Locale("Languages");;
//
//        Locale.setDefault(locale);
//        Configuration configuration = new Configuration();
//        configuration.locale = locale;
//        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
public void setlocale(String localeName) {
    if (!localeName.equals(currentLanguage)) {
        Locale myLocale;
        myLocale = new Locale(localeName);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        refresh.putExtra(currentLang, localeName);
        startActivity(refresh);
    } else {
        Toast.makeText(MainActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
    }

//        SharedPreferences.Editor editor = getSharedPreferences("Set", MODE_PRIVATE).edit();
//        editor.putString("App_lang", localeName);
//        editor.apply();
//        Toast.makeText(this, "....", Toast.LENGTH_SHORT).show();

    }
//public void loadLocale(){
//    SharedPreferences sharedPreferences = getSharedPreferences("Set", MODE_PRIVATE);
//    String language = sharedPreferences.getString("App_name","");
//    setlocale(language);
//
//}
}
