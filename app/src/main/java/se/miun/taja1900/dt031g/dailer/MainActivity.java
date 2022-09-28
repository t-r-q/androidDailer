package se.miun.taja1900.dt031g.dailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Boolean aboutB = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDial = findViewById(R.id.button_dial);
        btnDial.setOnClickListener(view -> openDialerActivity());

        Button btnCallList = findViewById(R.id.button_callList);
        btnCallList.setOnClickListener(view -> openCallListActivity());

        Button btnSettings = findViewById(R.id.button_settings);
        btnSettings.setOnClickListener(view -> openSettingsActivity());

        Button btnMaps = findViewById(R.id.button_map);
        btnMaps.setOnClickListener(view -> openMapsActivity());

        Button btnAbout = findViewById(R.id.button_about);
        btnAbout.setOnClickListener(view -> openAboutDialog());

    }

    public void openDialerActivity() {
        startActivity(new Intent(this, DialActivity.class));
    }

    public void openCallListActivity() {
        startActivity(new Intent(this, CallListActivity.class));
    }

    public void openSettingsActivity() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void openMapsActivity() {
        startActivity(new Intent(this, MapsActivity.class));
    }

    public void openAboutDialog() {
        if(aboutB)
            Toast.makeText(MainActivity.this, R.string.toast_message, Toast.LENGTH_SHORT).show();
        else {
            About about = new About();
            about.show(getSupportFragmentManager(), getString(R.string.About_dialog));
            aboutB = true;
        }
    }

}