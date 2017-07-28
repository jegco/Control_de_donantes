package com.example.jorgecaro.almacenamiento_jorge_caro.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jorge caro on 7/27/2017.
 */

public class Database extends SQLiteOpenHelper{
    private static final String name = "control_de_donantes";
    private static final int version=2;
    private static final String USER_TABLE = "user";
    private static final String DONOR_TABLE = "donante";
    private static final String[] USER_FIELDS = {"ID","NOMBRE","PASS"};
    private static final String[] DONOR_FIELDS = {"ID", "NOMBRE", "APELLIDO", "EDAD","ESTATURA", "SANGRE", "TIPO", "PESO"};

    private String create_users_table="CREATE TABLE "
            + USER_TABLE+ "("+USER_FIELDS[0]+" INT AUTO_INCREMENT,"
            + USER_FIELDS[1] +" TEXT NOT NULL, "
            + USER_FIELDS[2] +" TEXT NOT NULL,"
            +"PRIMARY KEY("+ USER_FIELDS[0] +"))";

    private String create_donors_table="CREATE TABLE "
            + DONOR_TABLE+ "("+DONOR_FIELDS[0]+" INT AUTO_INCREMENT,"
            + DONOR_FIELDS[1] +" TEXT NOT NULL, "
            + DONOR_FIELDS[2] +" TEXT NOT NULL, "
            + DONOR_FIELDS[3] +" INT NOT NULL, "
            + DONOR_FIELDS[4] +" INT NOT NULL, "
            + DONOR_FIELDS[5] +" TEXT NOT NULL,"
            + DONOR_FIELDS[6] +" TEXT NOT NULL, "
            + DONOR_FIELDS[7] +" INT NOT NULL, "
            + "PRIMARY KEY("+ DONOR_FIELDS[0] +"))";

    public Database(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_users_table);
        db.execSQL(create_donors_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDrop="DROP TABLE IF EXISTS "+USER_TABLE;
        String sqlDrop2="DROP TABLE IF EXISTS "+DONOR_TABLE;
        db.execSQL(sqlDrop);
        db.execSQL(sqlDrop2);
        onCreate(db);
    }

    public static String getName() {
        return name;
    }

    public static int getVersion() {
        return version;
    }

    public static String getUserTable() {
        return USER_TABLE;
    }

    public static String getDonorTable() {
        return DONOR_TABLE;
    }

    public static String[] getUserFields() {
        return USER_FIELDS;
    }

    public static String[] getDonorFields() {
        return DONOR_FIELDS;
    }

}
