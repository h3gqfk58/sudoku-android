package com.example.sudoku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_1 = "TABLE_1";
    public static final String T1_C1_SQ = "SQ";
    public static final String T1_C2_SA = "SA";
    public static final String T1_C3_SW = "SW";
    public static final String T1_C4_SE = "SE";
    public static final String T1_C5_SE3D_1 = "SE3D_1";
    public static final String T1_C6_SE3D_2 = "SE3D_2";
    public static final String T1_C7_SE3D_3 = "SE3D_3";
    public static final String T1_C8_SE3D_4 = "SE3D_4";
    public static final String T1_C9_SE3D_5 = "SE3D_5";
    public static final String T1_C10_SE3D_6 = "SE3D_6";
    public static final String T1_C11_SE3D_7 = "SE3D_7";
    public static final String T1_C12_SE3D_8 = "SE3D_8";
    public static final String T1_C13_SE3D_9 = "SE3D_9";
    public static final String T1_C14_X1 = "X1";
    public static final String T1_C15_X2 = "X2";
    public static final String T1_C16_X3 = "X3";
    public static final String T1_C17_X4 = "X4";
    public static final String T1_C18_X5 = "X5";

    public static final String TABLE_2 = "TABLE_2";
    public static final String T2_C1_RESUME_STATUS = "RESUME_STATUS";
    public static final String T2_C2_LEVEL = "LEVEL";
    public static final String T2_C3_VALIDATION_STATUS = "VALIDATION_STATUS";
    public static final String T2_C4_NOTES_STATUS = "NOTES_STATUS";
    public static final String T2_C5_TIME = "TIME";
    public static final String T2_C6_MISTAKES = "MISTAKES";
    public static final String T2_C7_GAME_MODE = "GAME_MODE";
    public static final String T2_C8_X1 = "X1";
    public static final String T2_C9_X2 = "X2";
    public static final String T2_C10_X3 = "X3";
    public static final String T2_C11_X4 = "X4";
    public static final String T2_C12_X5 = "X5";

    public static final String TABLE_3 = "TABLE_3";
    public static  final String T3_C1_GAME_MODE = "GAME_MODE";
    public static final String T3_C2_X1 = "X1";
    public static final String T3_C3_X2 = "X2";
    public static final String T3_C4_X3 = "X3";
    public static final String T3_C5_X4 = "X4";
    public static final String T3_C6_X5 = "X5";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "sudokuDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable1Statement = "CREATE TABLE "+TABLE_1+" ("+T1_C1_SQ+" INTEGER , "+T1_C2_SA+" INTEGER, "+T1_C3_SW+" INTEGER, "+T1_C4_SE+" INTEGER, "+T1_C5_SE3D_1+" INTEGER, "+T1_C6_SE3D_2+" INTEGER, "+T1_C7_SE3D_3+" INTEGER, "+T1_C8_SE3D_4+" INTEGER, "+T1_C9_SE3D_5+" INTEGER, "+T1_C10_SE3D_6+" INTEGER, "+T1_C11_SE3D_7+" INTEGER, "+T1_C12_SE3D_8+" INTEGER, "+T1_C13_SE3D_9+" INTEGER, "+T1_C14_X1+" TEXT, "+T1_C15_X2+" TEXT, "+T1_C16_X3+" TEXT, "+T1_C17_X4+" TEXT, "+T1_C18_X5+" TEXT)";
        db.execSQL(createTable1Statement);
        String createTable2Statement = "CREATE TABLE "+TABLE_2+" ("+T2_C1_RESUME_STATUS+" INTEGER , "+T2_C2_LEVEL+" INTEGER, "+T2_C3_VALIDATION_STATUS+" TEXT, "+T2_C4_NOTES_STATUS+" TEXT, "+T2_C5_TIME+" INTEGER, "+T2_C6_MISTAKES+" INTEGER, "+T2_C7_GAME_MODE+" TEXT, "+T2_C8_X1+" TEXT, "+T2_C9_X2+" TEXT, "+T2_C10_X3+" TEXT, "+T2_C11_X4+" TEXT, "+T2_C12_X5+" TEXT)";
        db.execSQL(createTable2Statement);
        String createTable3Statement = "CREATE TABLE "+TABLE_3+" ("+T3_C1_GAME_MODE+" TEXT, "+T3_C2_X1+" TEXT, "+T3_C3_X2+" TEXT, "+T3_C4_X3+" TEXT, "+T3_C5_X4+" TEXT, "+T3_C6_X5+" TEXT)";
        db.execSQL(createTable3Statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne_T1(Table_1 table_1) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(T1_C1_SQ, table_1.getSq());
        cv.put(T1_C2_SA, table_1.getSa());
        cv.put(T1_C3_SW, table_1.getSw());
        cv.put(T1_C4_SE, table_1.getSe());
        cv.put(T1_C5_SE3D_1, table_1.getSe3d_1());
        cv.put(T1_C6_SE3D_2, table_1.getSe3d_2());
        cv.put(T1_C7_SE3D_3, table_1.getSe3d_3());
        cv.put(T1_C8_SE3D_4, table_1.getSe3d_4());
        cv.put(T1_C9_SE3D_5, table_1.getSe3d_5());
        cv.put(T1_C10_SE3D_6, table_1.getSe3d_6());
        cv.put(T1_C11_SE3D_7, table_1.getSe3d_7());
        cv.put(T1_C12_SE3D_8, table_1.getSe3d_8());
        cv.put(T1_C13_SE3D_9, table_1.getSe3d_9());
        cv.put(T1_C14_X1, table_1.getX1());
        cv.put(T1_C15_X2, table_1.getX2());
        cv.put(T1_C16_X3, table_1.getX3());
        cv.put(T1_C17_X4, table_1.getX4());
        cv.put(T1_C18_X5, table_1.getX5());

        long insert = db.insert(TABLE_1, null, cv);

        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteAll_T1() {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+TABLE_1;

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<Table_1> getAll_T1() {
        List<Table_1> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_1;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                int sq = cursor.getInt(0);
                int sa = cursor.getInt(1);
                int sw = cursor.getInt(2);
                int se = cursor.getInt(3);
                int se3d_1 = cursor.getInt(4);
                int se3d_2 = cursor.getInt(5);
                int se3d_3 = cursor.getInt(6);
                int se3d_4 = cursor.getInt(7);
                int se3d_5 = cursor.getInt(8);
                int se3d_6 = cursor.getInt(9);
                int se3d_7 = cursor.getInt(10);
                int se3d_8 = cursor.getInt(11);
                int se3d_9 = cursor.getInt(12);
                String x1 = cursor.getString(13);
                String x2 = cursor.getString(14);
                String x3 = cursor.getString(15);
                String x4 = cursor.getString(16);
                String x5 = cursor.getString(17);



                Table_1 newTable_1 = new Table_1(sq, sa, sw, se, se3d_1, se3d_2, se3d_3, se3d_4, se3d_5, se3d_6, se3d_7, se3d_8, se3d_9, x1, x2, x3, x4, x5);
                returnList.add(newTable_1);
            }
            while(cursor.moveToNext());
        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addOne_T2(Table_2 table_2) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(T2_C1_RESUME_STATUS, table_2.getResume_status());
        cv.put(T2_C2_LEVEL, table_2.getLevel());
        cv.put(T2_C3_VALIDATION_STATUS, table_2.getValidation_status());
        cv.put(T2_C4_NOTES_STATUS, table_2.getNotes_status());
        cv.put(T2_C5_TIME, table_2.getTime());
        cv.put(T2_C6_MISTAKES, table_2.getMistakes());
        cv.put(T2_C7_GAME_MODE, table_2.getGame_mode());
        cv.put(T2_C8_X1, table_2.getX1());
        cv.put(T2_C9_X2, table_2.getX2());
        cv.put(T2_C10_X3, table_2.getX3());
        cv.put(T2_C11_X4, table_2.getX4());
        cv.put(T2_C12_X5, table_2.getX5());

        long insert = db.insert(TABLE_2, null, cv);

        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteAll_T2() {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+TABLE_2;

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<Table_2> getAll_T2() {
        List<Table_2> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_2;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                int resume_status = cursor.getInt(0);
                int level = cursor.getInt(1);
                String validation_status = cursor.getString(2);
                String notes_status = cursor.getString(3);
                long time = cursor.getLong(4);
                int mistakes = cursor.getInt(5);
                String game_mode = cursor.getString(6);
                String x1 = cursor.getString(7);
                String x2 = cursor.getString(8);
                String x3 = cursor.getString(9);
                String x4 = cursor.getString(10);
                String x5 = cursor.getString(11);


                Table_2 newTable_2 = new Table_2(resume_status, level, validation_status, notes_status, time, mistakes, game_mode, x1, x2, x3, x4, x5);
                returnList.add(newTable_2);
            }
            while(cursor.moveToNext());
        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addOne_T3(Table_3 table_3) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(T3_C1_GAME_MODE, table_3.getGame_mode());
        cv.put(T3_C2_X1, table_3.getX1());
        cv.put(T3_C3_X2, table_3.getX2());
        cv.put(T3_C4_X3, table_3.getX3());
        cv.put(T3_C5_X4, table_3.getX4());
        cv.put(T3_C6_X5, table_3.getX5());

        long insert = db.insert(TABLE_3, null, cv);

        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteAll_T3() {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+TABLE_3;

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<Table_3> getAll_T3() {
        List<Table_3> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_3;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                String game_mode = cursor.getString(0);
                String x1 = cursor.getString(1);
                String x2 = cursor.getString(2);
                String x3 = cursor.getString(3);
                String x4 = cursor.getString(4);
                String x5 = cursor.getString(5);

                Table_3 newTable_3 = new Table_3(game_mode, x1, x2, x3, x4, x5);
                returnList.add(newTable_3);
            }
            while(cursor.moveToNext());
        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }
}
