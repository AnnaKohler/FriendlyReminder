package ru.workingcorgi.friendlyreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 26.06.2017.
 */

public class DBHandler extends SQLiteOpenHelper implements IDBHandler{

    private static final String DATABASE_NAME="FREMINDER_BASE";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_TODO = "toDo";
    private static final String KEY_ID = "toDo_id";
    private static final String KEY_TITLE="toDo_title";
    private static final String KEY_DONE="toDo_done";


    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE="CREATE TABLE "+ TABLE_TODO +"("+ KEY_TITLE + " STRING, "+ KEY_DONE+" INTEGER"+")";
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TODO);
        onCreate(db);
    }

    @Override
    public void addToDo(ToDo toDo) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_TITLE, toDo.getTitle());
        cv.put(KEY_DONE, toDo.isDone());

        db.insert(TABLE_TODO, null, cv);
        //db.close();
    }

    @Override
    public void deleteToDo(ToDo toDo) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_TITLE+"=?", new String[]{toDo.getTitle()});
        //db.close();
    }

    @Override
    public void updateToDo(ToDo toDo) { //TODO: доделать, если понадобится

    }

    @Override
    public void clearAll() {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_TODO, null, null);
        db.close();
    }

    @Override
    public ArrayList<ToDo> getAllToDos() {
        ArrayList<ToDo> toDoList = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ TABLE_TODO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                ToDo toDo=new ToDo();
                toDo.setTitle(cursor.getString(0));
                toDo.setDone(Integer.parseInt(cursor.getString(1)) !=0);

                toDoList.add(toDo);

            }while (cursor.moveToNext());
        }
        return toDoList;
    }
}
