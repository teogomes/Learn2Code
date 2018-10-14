package com.example.teogomes.unipiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by teogomes on 01/12/2017.
 */

public class DBQuestions extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME = "Questions";
    public   String TABLE_NAME = "Questions";


    public DBQuestions(Context context) {
        super(context, DATABASE_NAME, null  , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+"(ID INTEGER PRIMARY KEY ,KIND INTEGER ,QUESTION TEXT ,ANSWER1 TEXT,ANSWER2 TEXT,ANSWER3 TEXT,ANSWER4 TEXT,RIGHT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public  void deleteall(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }

    public void addQuestion(int id , int kind , String question , String answer1 , String answer2 , String answer3, String answer4, int right) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID",id);
        values.put("KIND",kind);
        values.put("QUESTION",question);
        values.put("ANSWER1",answer1);
        values.put("ANSWER2",answer2);
        values.put("ANSWER3",answer3);
        values.put("ANSWER4",answer4);
        values.put("RIGHT",right);

        db.insert(TABLE_NAME,null,values);
        db.close();

    }


    //not usable
    public Cursor getQuestion(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID=\""+ID+"\"",null,null);
        return data;

    }

    public void deleteRowbyName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"NAME=?",new String[]{name});

    }
}
