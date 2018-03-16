package com.example.jorgecaro.almacenamiento_jorge_caro.repositories.Local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jorgecaro.almacenamiento_jorge_caro.model.User;

import java.util.List;

/**
 * Created by jorge caro on 7/27/2017.
 */

public class UserDao implements IDao<User>{

    private static UserDao instance;
    private Database database;

    private UserDao(Context context){
        this.database = new Database(context);
    }

    public static UserDao getInstance(Context context){
        if(instance == null) instance = new UserDao(context);
        return instance;
    }

    @Override
    public boolean add(User user){
        SQLiteDatabase sq = database.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Database.getUserFields()[1], user.getNombre());
        valores.put(Database.getUserFields()[2], user.getPass());
        long res = sq.insert(Database.getUserTable(),null, valores);
        return res > 0;
    }

    public boolean checkIfExist(User user){
        SQLiteDatabase sq = database.getWritableDatabase();
        Cursor cursor = sq.rawQuery("SELECT * FROM USER WHERE " +Database.getUserFields()[1]+" = ? AND " +Database.getUserFields()[2]+" = ?"
                                    , new String[]{user.getNombre()
                                    , user.getPass()});
        return cursor.getCount() > 0;
    }

    public boolean update(User user){
        SQLiteDatabase sq = database.getWritableDatabase();
        ContentValues valores = new ContentValues();
        long res = sq.update(Database.getUserTable(),
                            valores,Database.getUserFields()[0]+" = ? "
                            , new String[]{user.getId()+""});
        return res > 0;
    }

    @Override
    public List<User> search(int id) {
        return null;
    }

    @Override
    public boolean delete(User user){
        SQLiteDatabase sq = database.getWritableDatabase();
        return sq.delete(Database.getUserTable(),
                        Database.getUserFields()[1] + "= ?"
                        +" AND "+Database.getUserFields()[2] + "= ?"
                        , new String[]{ user.getNombre(),  user.getPass()}) > 0;
    }

    @Override
    public List<User> search() {
        return null;
    }
}
