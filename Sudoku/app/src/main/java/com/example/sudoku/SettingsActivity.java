package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup game_mode_radio_group;
    RadioButton default_radio,raw_radio,appa_radio;
    View dummy;

    public void save_changes(View view) {
        int f1=0;

        DataBaseHelper dataBaseHelper = new DataBaseHelper(SettingsActivity.this);
        dataBaseHelper.deleteAll_T3();
        Table_3 table_3;
        table_3 = new Table_3("default", "", "", "", "", "");

        if(raw_radio.isChecked()) {
            f1=1;
            table_3 = new Table_3("raw", "", "", "", "", "");
        }
        else if(default_radio.isChecked()) {
            f1=1;
            table_3 = new Table_3("default", "", "", "", "", "");
        }
        else if(appa_radio.isChecked()) {
            f1=1;
            table_3 = new Table_3("appa", "", "", "", "", "");
        }

        if(f1==1) {
            boolean success = dataBaseHelper.addOne_T3(table_3);
            Toast.makeText(getApplicationContext(), "Saved Changes", Toast.LENGTH_SHORT).show();
        }
    }

    public void end(View view) { finish(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        default_radio = (RadioButton) findViewById(R.id.default_radio);
        raw_radio = (RadioButton) findViewById(R.id.raw_radio);
        appa_radio = (RadioButton) findViewById(R.id.appa_radio);
        game_mode_radio_group = (RadioGroup) findViewById(R.id.game_mode_radio_group);

        String game_mode_recieved;

        DataBaseHelper dataBaseHelper = new DataBaseHelper(SettingsActivity.this);
        List<Table_3> recievedTable = dataBaseHelper.getAll_T3();

        if(recievedTable.size() != 0)  {
            game_mode_recieved = String.valueOf(recievedTable.get(0).getGame_mode());
            if(game_mode_recieved.equals("raw")) {
                raw_radio.setChecked(true);
            }
            else if(game_mode_recieved.equals("default")) {
                default_radio.setChecked(true);
            }
            else if(game_mode_recieved.equals("appa")) {
                appa_radio.setChecked(true);
            }
        }
        else {
            default_radio.setChecked(true);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_puzzle, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if (id == R.id.action_home) end(dummy);

        return super.onOptionsItemSelected(item);
    }
}