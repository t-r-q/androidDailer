package se.miun.taja1900.dt031g.dailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.os.Bundle;

public class CallListActivity extends AppCompatActivity {
    Dialpad dialpad;
    ListView callListView;
    ArrayList<String> callList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list);

        getSupportActionBar().setTitle("Call list");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dialpad = (Dialpad)findViewById(R.id.dialpad_dial_activity);
        callListView = (ListView)findViewById(R.id.listView_callList);

        callList = getCallList();
        if (callList == null || callList.isEmpty()){
            callList = new ArrayList<String>();
            callList.add("No telephone number are stored!");
        }
        ArrayAdapter arr = new ArrayAdapter(this, android.R.layout.simple_list_item_1, callList);
        callListView.setAdapter(arr);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_list_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.callList_del_menu:
                ArrayList<String> emptyList = new ArrayList<String>();
                storeNumbers(emptyList);
                finish();
                startActivity(getIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private ArrayList<String> getCallList(){
        SharedPreferences shPref = getSharedPreferences(DialActivity.callListName, MODE_PRIVATE);
        String jsonString = shPref.getString(DialActivity.callListKey, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(jsonString, type);
    }

    private void storeNumbers(ArrayList<String> callList){
        Gson gson = new Gson();
        String jsonString = gson.toJson(callList);

        SharedPreferences shPref = getSharedPreferences(DialActivity.callListName, MODE_PRIVATE);
        SharedPreferences.Editor edit = shPref.edit();
        edit.putString(DialActivity.callListKey, jsonString);
        edit.apply();
    }
}