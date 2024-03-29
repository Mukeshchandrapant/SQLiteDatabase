package in.mvi.sqlitefirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static final String DATABASE_NAME = "student.db";
    public  static final String TABLE_NAME = "student_table";
    public  static final String Col_1 = "ID";
    public  static final String Col_2 = "NAME";
    public  static final String Col_3 = "SURNAME";
    public  static final String Col_4 = "MARKS";


    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER )");

        Log.d("Database Operations", "Table created... enjoy your coding...!!!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, name);
        contentValues.put(Col_3, surname);
        contentValues.put(Col_4, marks);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        } else
            return true;
    }

    public Cursor getAllStudent(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public boolean updateStudentData(String id, String name, String surname, String marks){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, id);
        contentValues.put(Col_2, name);
        contentValues.put(Col_3, surname);
        contentValues.put(Col_4, marks);
        db.update(TABLE_NAME, contentValues, "ID = ? " ,new String [] { id } );
        return  true;
    }

    public Integer deleteStudetData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "ID= ?", new String[] { id });

    }
}


