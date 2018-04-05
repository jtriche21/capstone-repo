package com.example.vanessaperry.demoappscreens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by vanessaperry on 4/1/18.
 */

public class MapScreen extends Activity{

    Button active;
    Button inactive;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapscreen);

        active = findViewById(R.id.professer4_activebutton);
        inactive = findViewById(R.id.professer1_inactivebutton);
    }

    public void Inactive(View view){
        Toast.makeText(this, "Come Closer To Retrieve Information!", Toast.LENGTH_LONG).show();
    }

    public void Active(View view){
        Intent i = new Intent(MapScreen.this, ProfHome.class);
        startActivity(i);
    }
}
