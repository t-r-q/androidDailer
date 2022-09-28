package se.miun.taja1900.dt031g.dailer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Objects;


public class DialActivity extends AppCompatActivity {
    public static final String callListName = "CallList";
    public static final String callListKey = "list";

    Dialpad dialpad;
    ImageButton call;
    ArrayList<String> callList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_dial);
        SoundPlayer.getInstance(this);


        dialpad = findViewById(R.id.dialpad_dial_activity);
        call = findViewById(R.id.call_button);
        Boolean saveState = shouldStoreNumbers(this);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation(v);
                String text = "";
                try {
                    text = URLEncoder.encode(dialpad.text(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                callList = getCallList();
                if (callList == null){
                    callList = new ArrayList<String>();
                }
                callList.add(dialpad.text());
                if (saveState){
                    storeNumbers(callList);
                }
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + (String) text));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundPlayer.getInstance(this).destroy();
    }

    public void animation(View v){
        Animation animation = AnimationUtils.loadAnimation(v.getContext(),R.anim.blink_anim);
        v.startAnimation(animation);
    }

    private void storeNumbers(ArrayList<String> callList){
        Gson gson = new Gson();
        String jsonString = gson.toJson(callList);

        SharedPreferences shPref = getSharedPreferences(callListName, MODE_PRIVATE);
        SharedPreferences.Editor edit = shPref.edit();
        edit.putString("list", jsonString);
        edit.apply();
    }

    private ArrayList<String> getCallList(){
        SharedPreferences shPref = getSharedPreferences(callListName, MODE_PRIVATE);
        String jsonString = shPref.getString(callListKey, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(jsonString, type);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dial_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.dial_setting:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean shouldStoreNumbers(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(
                context.getString(R.string.switchPreference_key), true);

    }
}