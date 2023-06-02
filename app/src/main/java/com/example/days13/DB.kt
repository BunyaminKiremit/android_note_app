package com.example.days13

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.days13.models.Note

class DB(context: Context):SQLiteOpenHelper(context, DBName,null, Version) {
    companion object{
        private val DBName="notes"
        private val Version=1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val noteTable="CREATE TABLE \"note\" (\n" +
                "\t\"nid\"\tINTEGER,\n" +
                "\t\"title\"\tTEXT,\n" +
                "\t\"detail\"\tTEXT,\n" +
                "\t\"date\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"nid\" AUTOINCREMENT)\n" +
                ");"
        db?.execSQL(noteTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val noteTableDrop="DROP TABLE IF EXISTS note"
        db?.execSQL(noteTableDrop)
        onCreate(db)
    }

    fun addNote(title:String,detail:String,date:String):Long{
        val db=this.writableDatabase
        val values=ContentValues()
        values.put("title",title)
        values.put("detail",detail)
        values.put("date",date)

        val effectRow=db.insert("note",null,values)

        db.close()
        return effectRow

    }
    fun deleteNote(nid:Int):Int{
        val db=this.writableDatabase
        val status=db.delete("note","nid=${nid}",null)
        db.close()
        return status
    }

    fun updateNote(title:String,detail:String,nid: Int):Int{
        val db=this.writableDatabase
        val content=ContentValues()
        content.put("title",title)
        content.put("detail",detail)
        val status=db.update("note",content,"nid =$nid",null)
        db.close()
        return status
    }

    fun allNote(): List<Note> {
        val db=this.readableDatabase
        val arr= mutableListOf<Note>()
        val cursor=db.query("note",null,null,null,null,null,null)

        while (cursor.moveToNext()){

            val nid=cursor.getInt(0)
            val title=cursor.getString(1)
            val detail=cursor.getString(2)
            val date=cursor.getString(3)

            val note=Note(nid,title,detail,date)
            arr.add(note)

        }
        db.close()
        return arr
    }
}
