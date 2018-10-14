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

import static android.media.MediaCodec.MetricsConstants.MODE;

public class LearnActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout basicLesson , conditionsLesson , loopLesson , objectsLesson;
    private ProgressBar[] progressBars = new ProgressBar[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initComps();

        SharedPreferences prefs = getSharedPreferences("LOGGED",MODE_PRIVATE);
        int lp = prefs.getInt("LP",0);
        Log.d("LP FROM LEARN ",String.valueOf(lp));
        for(int i=0;i<lp;i++){
            progressBars[i/4].setProgress(progressBars[i/4].getProgress() + 1);
        }
    }

    private void  initComps(){
        basicLesson = (RelativeLayout)findViewById(R.id.basicLesson);
        conditionsLesson = (RelativeLayout)findViewById(R.id.conditionsLesson);
        loopLesson = (RelativeLayout)findViewById(R.id.loopsLesson);
        objectsLesson = (RelativeLayout)findViewById(R.id.objectsLessons);

        basicLesson.setOnClickListener(this);
        conditionsLesson.setOnClickListener(this);
        loopLesson.setOnClickListener(this);
        objectsLesson.setOnClickListener(this);

        progressBars[0] = (ProgressBar)findViewById(R.id.basicProg);
        progressBars[1] = (ProgressBar)findViewById(R.id.conditionProg);
        progressBars[2] = (ProgressBar)findViewById(R.id.loopProg);
        progressBars[3] = (ProgressBar)findViewById(R.id.objectProg);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),pdfReaderActivity.class);
        switch(v.getId()) {
            case R.id.basicLesson:
                intent.putExtra("FROM", 0);
                break;
            case R.id.conditionsLesson:
                intent.putExtra("FROM", 4);
                break;
            case R.id.loopsLesson:
                intent.putExtra("FROM", 8);
                break;
            case R.id.objectsLessons:
                intent.putExtra("FROM", 12);
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
