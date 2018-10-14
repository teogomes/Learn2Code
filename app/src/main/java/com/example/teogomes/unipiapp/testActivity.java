package com.example.teogomes.unipiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class testActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout basicTest , loopTest, conditionTest , objectTest;
    private ProgressBar[] progressBars = new ProgressBar[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences prefs = getSharedPreferences("LOGGED",MODE_PRIVATE);
        int tp = prefs.getInt("TP",0);
        Log.d("TP FROM LEARN ",String.valueOf(tp));
        for(int i=0;i<tp;i++){
            progressBars[i/4].setProgress(progressBars[i/4].getProgress() + 1);
        }

        initComps();
    }


    private void initComps(){
        basicTest = (RelativeLayout)findViewById(R.id.basicTest);
        conditionTest = (RelativeLayout)findViewById(R.id.conditionsTest);
        loopTest = (RelativeLayout)findViewById(R.id.loopsTest);
        objectTest = (RelativeLayout)findViewById(R.id.objectsTest);

        basicTest.setOnClickListener(this);
        conditionTest.setOnClickListener(this);
        loopTest.setOnClickListener(this);
        objectTest.setOnClickListener(this);

        progressBars[0] = (ProgressBar)findViewById(R.id.basicTestProg);
        progressBars[1] = (ProgressBar)findViewById(R.id.conditionTestProg);
        progressBars[2] = (ProgressBar)findViewById(R.id.loopTestProg);
        progressBars[3] = (ProgressBar)findViewById(R.id.objectTestProg);



    }




    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),questionsActivity.class);
        switch (v.getId()){
            case R.id.basicTest:
                intent.putExtra("FROM",0);
                break;
            case R.id.conditionsTest:
                intent.putExtra("FROM",4);
                break;
            case R.id.loopsTest:
                intent.putExtra("FROM",8);
                break;
            case R.id.objectsTest:
                intent.putExtra("FROM",12);
                break;

        }
        startActivity(intent);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
