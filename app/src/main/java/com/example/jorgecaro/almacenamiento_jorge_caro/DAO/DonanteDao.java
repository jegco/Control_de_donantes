package com.example.jorgecaro.almacenamiento_jorge_caro.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Donante;
import com.example.jorgecaro.almacenamiento_jorge_caro.Modelo.Usuario;

/**
 * Created by jorge caro on 7/27/2017.
 */

public class DonanteDao {
    public static boolean insert_donante(Donante donante, Database db){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Database.getDonorFields()[0],donante.getId());
        valores.put(Database.getDonorFields()[1],donante.getNombre());
        valores.put(Database.getDonorFields()[2],donante.getApellido());
        valores.put(Database.getDonorFields()[3],donante.getEdad());
        valores.put(Database.getDonorFields()[4],donante.getEstatura());
        valores.put(Database.getDonorFields()[5],donante.getSangre());
        valores.put(Database.getDonorFields()[6],donante.getTipo());
        valores.put(Database.getDonorFields()[7],donante.getPeso());
        long res = sq.insert(Database.getDonorTable(),null, valores);
        if (res>0) return true;
        return false;
    }

    public static boolean search_donante(Donante donante, Database db){
        SQLiteDatabase sq = db.getWritableDatabase();
        Cursor cursor = sq.rawQuery("SELECT * FROM "+Database.getDonorTable()+" LIKE " +Database.getUserFields()[0]+" = ?"
                , new String[]{donante.getNombre()
                        ,donante.getId()+""});
        if (cursor.getCount()>0) return true;
        return false;
    }

    public static boolean update_donante(Donante donante, Database db){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Database.getDonorFields()[1],donante.getNombre());
        valores.put(Database.getDonorFields()[2],donante.getApellido());
        valores.put(Database.getDonorFields()[3],donante.getEdad());
        valores.put(Database.getDonorFields()[4],donante.getEstatura());
        valores.put(Database.getDonorFields()[5],donante.getSangre());
        valores.put(Database.getDonorFields()[6],donante.getTipo());
        valores.put(Database.getDonorFields()[7],donante.getPeso());
        long res = sq.update(Database.getDonorTable(),
                valores,"WHERE "+Database.getDonorFields()[0]+" = ?"
                , new String[]{donante.getNombre(),donante.getId()+""});
        if (res>0) return true;
        return false;
    }

    public static boolean delete_donante(Donante donante, Database db){
        SQLiteDatabase sq = db.getWritableDatabase();
        return sq.delete(Database.getDonorTable(),
                Database.getDonorFields()[1] + "=" + donante.getNombre()
                        +" AND "+Database.getDonorFields()[0] + "=" + donante.getId()
                , null) > 0;
    }
}
