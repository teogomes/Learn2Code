package com.example.teogomes.unipiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

public class pdfReaderActivity extends AppCompatActivity {

    private PDFView pdfView;
    private DBhelper db;
    private int from,lp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_reader);
        Intent intent = getIntent();
         from = intent.getIntExtra("FROM",0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initPDF();
    }

    private void initPDF(){
        pdfView = (PDFView)findViewById(R.id.pdfView);
        pdfView.fromAsset("test.pdf").load();
        db = new DBhelper(getApplicationContext());
    }

    public void nextPage(View view){
        pdfView.jumpTo(pdfView.getCurrentPage() + 1 , true);
    }

    public void prevpage(View view){
        pdfView.jumpTo(pdfView.getCurrentPage() - 1 , true);
    }

    private void updateDB(int lp){
        SharedPreferences prefs = getSharedPreferences("LOGGED", MODE_PRIVATE);
        String email = prefs.getString("EMAIL", "null");
        Cursor data = db.login(email);
        while(data.moveToNext()){
            db.updateProgress(lp,data.getString(0));
            Log.d("ID FOUNDED" , data.getString(0));
        }
        Cursor data2 = db.login(email);
        while(data2.moveToNext()){
            Log.d("AFTER UPDATE LP= ", data2.getString(4));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        lp=pdfView.getCurrentPage() + from;
        switch (item.getItemId()) {
            case android.R.id.home:
                SharedPreferences prefs = getSharedPreferences("LOGGED",MODE_PRIVATE);
                if(prefs.getInt("LP",0) > lp ){
                    lp = prefs.getInt("LP",0);
                }
                SharedPreferences.Editor editor = getSharedPreferences("LOGGED", MODE_PRIVATE).edit();
                editor.putInt("LP",lp);
                editor.apply();
                updateDB(lp);
                Intent intent = new Intent(this, LearnActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
