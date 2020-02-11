package com.martinscastanho.marco.languagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static String language = null;
    static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.martinscastanho.marco.languagepreferences", Context.MODE_PRIVATE);
        language = sharedPreferences.getString("language", "Error");
        if(language.equals("Error")){
            showDialog();
        } else{
            setText(language);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.language_english:
                setLanguage("English");
                return true;
            case R.id.language_spanish:
                setLanguage("Spanish");
                return true;
            default:
                return false;
        }
    }

    public void showDialog(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Language")
                .setMessage("Which language do you want?")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("English");
                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage("Spanish");
                    }
                })
                .show();
    }

    public void setLanguage(String lang){
        sharedPreferences.edit().putString("language", lang).apply();
        setText(lang);
    }

    public void setText(String lang){
        TextView textView = findViewById(R.id.helloTextView);
        switch (lang){
            case "English":
                textView.setText("Hello World!");
                break;
            case "Spanish":
                textView.setText("¡Hola Mundo!");
        }
    }
}
