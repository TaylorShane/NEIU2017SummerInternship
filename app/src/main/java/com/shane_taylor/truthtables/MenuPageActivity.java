package com.shane_taylor.truthtables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MenuPageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu_page);
    }

    protected void onClickTruth(View view){
        Intent intent = new Intent(this, TruthTables.class);
        startActivity(intent);
    }

    protected void onClickPoint(View view){
        Intent intent = new Intent(this, Graph1.class);
        startActivity(intent);
    }

    protected void onClickLine(View view){
        Intent intent = new Intent(this, Graph2.class);
        startActivity(intent);
    }

    protected void onClickReflexive(View view){
        Intent intent = new Intent(this, Graph3.class);
        startActivity(intent);
    }

    protected void onClickSSS(View view){
        Intent intent = new Intent(this, Graph4.class);
        startActivity(intent);
    }

    protected void onClickPythag(View view){
        Intent intent = new Intent(this, Graph5.class);
        startActivity(intent);
    }
}