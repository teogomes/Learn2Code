package com.example.teogomes.unipiapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class questionsActivity extends AppCompatActivity {

    private RadioButton radio1 , radio2 , radio3 , radio4;
    private RadioGroup rg;
    private TextView questionView;
    private EditText answerText;
    private DBQuestions db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initComps();
      // addQuestions();
        getQuestions();
        Log.d("Test Git","GIT TEST");
    }

    private void initComps(){
        radio1 = (RadioButton)findViewById(R.id.radioAns1);
        radio2 = (RadioButton)findViewById(R.id.radioAns2);
        radio3 = (RadioButton)findViewById(R.id.radioAns3);
        radio4 = (RadioButton)findViewById(R.id.radioAns4);
        questionView = (TextView)findViewById(R.id.questionView);
        answerText = (EditText) findViewById(R.id.answerText);

        rg = (RadioGroup) findViewById(R.id.rg);

        db = new DBQuestions(getApplicationContext());
    }

    private void getQuestions(){

        Cursor data = db.getQuestion("3");
        while(data.moveToNext()){
            if(data.getInt(1) == 0){
                answerText.setVisibility(View.GONE);
                rg.setVisibility(View.VISIBLE);
                radio1.setText(data.getString(3));
                radio2.setText(data.getString(4));
                radio3.setText(data.getString(5));
                radio4.setText(data.getString(6));
            }else{
                answerText.setVisibility(View.VISIBLE);
                rg.setVisibility(View.GONE);

            }
            questionView.setText(data.getString(2));

        }



    }

    private void addQuestions(){

//        db.addQuestion(0,0,"Which of the following variable names are allowed?","2name","_name2","@string","name-2",4);
        db.addQuestion(3,1,"x = 5 ; \n  Console.WriteLine(x++)","6","","","",0);
    }
}
