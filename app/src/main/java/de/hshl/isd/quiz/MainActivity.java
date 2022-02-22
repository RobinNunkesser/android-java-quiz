package de.hshl.isd.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.navigation.Navigation;
import de.hshl.isd.quiz.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_statistics:
                Navigation.findNavController(this, R.id.nav_host_fragment_container)
                        .navigate(R.id.action_mainFragment_to_statisticsFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}