package de.hshl.isd.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import de.hshl.isd.quiz.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}