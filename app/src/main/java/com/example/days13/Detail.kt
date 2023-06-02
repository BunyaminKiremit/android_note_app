package com.example.days13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import com.example.days13.adapter.NoteAdapter
import com.example.days13.adapter.TitleAdapter
import com.example.days13.models.Note

class Detail : AppCompatActivity() {

    lateinit var detailList:ListView
    lateinit var btnDelete:Button

    lateinit var db:DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailList=findViewById(R.id.detailList)
        btnDelete=findViewById(R.id.btnDelete)

        db=DB(this)

        val note = intent.getSerializableExtra("note") as Note
        Log.d("noteid",note.toString())
        val arr: MutableList<Note> = mutableListOf(note)
        val adapter = NoteAdapter(this, arr)
        detailList.adapter = adapter


        btnDelete.setOnClickListener(){
            val deleteStatus=db.deleteNote(note.nid)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
    }
}