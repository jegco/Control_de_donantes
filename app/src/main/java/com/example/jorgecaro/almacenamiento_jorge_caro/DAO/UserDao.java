package com.example.jorgecaro.almacenamiento_jorge_caro.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Usuario;

/**
 * Created by jorge caro on 7/27/2017.
 */

public class UserDao {

    public static boolean insert_user(Usuario usuario, Database db){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Database.getUserFields()[1],usuario.getNombre());
        valores.put(Database.getUserFields()[2],usuario.getPass());
        long res = sq.insert(Database.getUserTable(),null, valores);
        if (res>0) return true;
        return false;
    }

    public static boolean search_user(Usuario usuario, Database db){
        SQLiteDatabase sq = db.getWritableDatabase();
        Cursor cursor = sq.rawQuery("SELECT * FROM USER WHERE " +Database.getUserFields()[1]+" = ? AND " +Database.getUserFields()[2]+" = ?"
                                    , new String[]{usuario.getNombre()
                                    ,usuario.getPass()});
        if (cursor.getCount()>0) return true;
        return false;
    }

    public static boolean update_user(Usuario usuario, String new_pass, Database db){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Database.getUserFields()[2],new_pass);
        long res = sq.update(Database.getUserTable(),
                            valores,"WHERE "+Database.getUserFields()[1]+" = ? AND "+ Database.getUserFields()[2]+" = ?"
                            , new String[]{usuario.getNombre(),usuario.getPass()});
        if (res>0) return true;
        return false;
    }

    public static boolean delete_user(Usuario usuario, Database db){
        SQLiteDatabase sq = db.getWritableDatabase();
        return sq.delete(Database.getUserTable(),
                        Database.getDonorFields()[1] + "=" + usuario.getNombre()
                        +" AND "+Database.getUserFields()[2] + "=" + usuario.getPass()
                        , null) > 0;
    }
}
