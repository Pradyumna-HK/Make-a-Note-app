package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {

    String messege = "";
    EditText tv;
    int noteID;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
      tv = (EditText) findViewById(R.id.tv);
        Intent intent = getIntent();

       noteID = intent.getIntExtra("noteID",-1);
      if(noteID != -1){
          tv.setText(MainActivity.notes.get(noteID));
      }else {
          MainActivity.notes.add("");
          noteID = MainActivity.notes.size() - 1;
      }

      tv.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               MainActivity.notes.set(noteID, String.valueOf(charSequence));
              MainActivity.arrayAdapter.notifyDataSetChanged();
              SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
              HashSet<String> set = new HashSet<>(MainActivity.notes);
              sharedPreferences.edit().putStringSet("note",set).apply();


          }

          @Override
          public void afterTextChanged(Editable editable) {


          }
      });

    }
}