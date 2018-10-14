package com.example.teogomes.unipiapp;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private CardView learnCard , testCard , loginCard , contactCard ;
    private TextView helloText,loginCardText;
    public boolean logged = false;
    private ImageView icon;
    private ProgressBar progressBar;

    @Override
    public boolean releaseInstance() {
        return super.releaseInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComps();


    }

    private void  initComps(){
        helloText = (TextView) findViewById(R.id.logged);
        loginCardText = (TextView)findViewById(R.id.loginCardText);
        icon = (ImageView)findViewById(R.id.loginCardIcon);
        learnCard = (CardView)findViewById(R.id.learnCard);
        testCard = (CardView)findViewById(R.id.testCard);
        loginCard = (CardView)findViewById(R.id.loginCard);
        contactCard = (CardView)findViewById(R.id.contactCard);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        //Click Listeners
        learnCard.setOnClickListener(this);
        testCard.setOnClickListener(this);
        loginCard.setOnClickListener(this);
        contactCard.setOnClickListener(this);
        SharedPreferences prefs = getSharedPreferences("LOGGED", MODE_PRIVATE);
        String name = prefs.getString("NAME", "null");
        int lprog = prefs.getInt("LP",0);
        int tprog = prefs.getInt("TP",0);
        Log.d("TAG",String.valueOf(lprog));


        logged = prefs.getBoolean("LOGGED", false);
        if(logged){
            helloText.setVisibility(View.VISIBLE);
            helloText.setText("HELLO "+ name.toUpperCase()+" HOW ARE YOU TODAY?");
            icon.setImageDrawable(getDrawable(R.drawable.ic_highlight_off_black_24dp));
            loginCardText.setText("Αποσύνδεση");
        }else{
            loginCardText.setText("Σύνδεση");
            helloText.setVisibility(View.GONE);
            icon.setImageDrawable(getDrawable(R.drawable.ic_account_circle_black_24dp));
        }



    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.learnCard:
                startActivity(new Intent(getApplicationContext(),LearnActivity.class));
                break;
            case R.id.testCard:
                startActivity(new Intent(getApplicationContext(),testActivity.class));

                break;
            case R.id.loginCard:
                if(logged){
                    SharedPreferences.Editor editor = getSharedPreferences("LOGGED", MODE_PRIVATE).edit();
                    editor.putBoolean("LOGGED",false);
                    editor.apply();
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this,LoginActivity.class);
                    intent.putExtra("TO","LOGIN");
                    startActivity(intent);
                }
                break;

            case R.id.contactCard:

                break;

        }
    }
}


