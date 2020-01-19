package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdatbazisSegito extends SQLiteOpenHelper {

    public static User loggedinuser;
    private static final int DBversion = 1;
    private static final String DBname = "tanulok.db";

    private static final String TABLE_NAME = "tanulok";

    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_FELHASZ = "felhasz";
    private static final String COL_JELSZO = "jelszo";
    private static final String COL_TELJESNEV = "teljesnev";


    public AdatbazisSegito(Context context){
        super(context,DBname,null,DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTables = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(" +
                COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_EMAIL+" VARCHAR(30)," +
                COL_FELHASZ+ " VARCHAR(30)," +
                COL_JELSZO+ " VARCHAR(30)," +
                COL_TELJESNEV+ " VARCHAR(30))" ;
                db.execSQL(createTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean adatFelvetel(String email, String felhasz, String jelszo, String teljesnev){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHASZ, felhasz);
        values.put(COL_JELSZO, jelszo);
        values.put(COL_TELJESNEV, teljesnev);

        //return db.insert(TABLE_NAME, null, values) != -1;
        long erintettSorok = db.insert(TABLE_NAME, null, values);
        if (erintettSorok == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean Bejelentkezes(String felhasznalonev,String jelszo){
        SQLiteDatabase db = this.getReadableDatabase();
        User u= new User();
        Cursor Adat = this.getFelhasznaloAdat(felhasznalonev);
        if (Adat.getCount()==0)
        {
            return false;
        }
        while(Adat.moveToNext())
        {
            if (Adat.getString(1).equals(jelszo))
            {
                loggedinuser=new User();
                loggedinuser.setFelhasznalonev(felhasznalonev);
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public Cursor getFelhasznaloAdat(String felhasznalonev)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT felhasz,jelszo FROM "+TABLE_NAME +" WHERE felhasz ='"+felhasznalonev+"'", null);
    }

    public Cursor Bejelentkezes(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

    String nev;
    public String Bejelentkezoneve(String s) {
        nev = s;
        return s;
    }
    public String Bejelentk() {
        return nev;
    }

    public Cursor Bejelentkezo(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT teljesnev FROM "+TABLE_NAME, null);
    }

}
