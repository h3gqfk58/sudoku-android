package com.example.sudoku;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button resume;
    ImageView close_button;
    ConstraintLayout levels;
    ConstraintLayout loading_block;
    Animation animFadeIn,animFadeOut,animFromBottom,animToBottom;

    public void resume(View view) {
        loading_block.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, PuzzleActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", "4");
        startActivity(intent);
    }
    public void have_puzzle(View view) {
        loading_block.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, PuzzleActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", "0");
        startActivity(intent);
    }
    public void generate_level_1_puzzle(View view) {
        loading_block.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, PuzzleActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", "1");
        startActivity(intent);
    }
    public void generate_level_2_puzzle(View view) {
        loading_block.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, PuzzleActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", "2");
        startActivity(intent);
    }
    public void generate_level_3_puzzle(View view) {
        loading_block.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, PuzzleActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", "3");
        startActivity(intent);
    }
    public void new_puzzle(View view) {
        levels.setVisibility(View.VISIBLE);
        levels.startAnimation(animFromBottom);
    }
    public void close_new_puzzle(View view) {
        levels.startAnimation(animToBottom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        levels = (ConstraintLayout) findViewById(R.id.levels);
        close_button = (ImageView) findViewById(R.id.levels_close_button);
        loading_block = (ConstraintLayout) findViewById(R.id.loading_block);

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        animFromBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_bottom);
        animToBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_bottom);

        animToBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                levels.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        levels.setVisibility(View.GONE);

        resume = (Button) findViewById(R.id.resume);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        List<Table_2> recievedTable2 = dataBaseHelper.getAll_T2();
        if(recievedTable2.size() == 0)  resume.setEnabled(false);
        else resume.setEnabled(true);

        loading_block.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if (id == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}