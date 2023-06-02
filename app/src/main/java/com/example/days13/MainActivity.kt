package com.example.days13

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.days13.adapter.NoteAdapter
import com.example.days13.adapter.TitleAdapter
import com.example.days13.models.Note


class MainActivity : AppCompatActivity() {



    lateinit var edtTxtTitle:EditText
    lateinit var edtTxtDetail:EditText
    lateinit var btnDate:Button
    lateinit var btnSave:Button
    lateinit var listNote:ListView

    lateinit var db:DB
    var selectDate=""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtTxtTitle=findViewById(R.id.edtTxtTitle)
        edtTxtDetail=findViewById(R.id.edtTxtDetail)
        btnDate=findViewById(R.id.btnDate)
        btnSave=findViewById(R.id.btnSave)
        listNote=findViewById(R.id.listNote)

    }

    override fun onStart() {
        super.onStart()

        var title=edtTxtTitle.text
        var detail=edtTxtDetail.text
        db=DB(this)

        val arr: MutableList<Note> = db.allNote().toMutableList()

        val adapter = TitleAdapter(this, arr)
        listNote.adapter = adapter
        adapter.notifyDataSetChanged()

        listNote.onItemClickListener=
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this, Detail::class.java)
                intent.putExtra("note", arr[position]as java.io.Serializable)
                startActivity(intent)
            }


        val calender=Calendar.getInstance()
        btnDate.setOnClickListener ({
            val datePickerDialog=DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    var ay = "${i2 + 1}"
                    if (i2 + 1 < 10) {
                        ay = "0${i2 + 1}"
                    }
                    selectDate = "$i3.$ay.$i"
                },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH),
            )
            datePickerDialog.show()
        })

        btnSave.setOnClickListener{
            if (selectDate!="") {
                var status = db.addNote("$title", "$detail", selectDate)
                selectDate=""
                val ls=db.allNote()
                arr.clear() // Tüm verileri temizle
                arr.addAll(db.allNote()) // Yeni verileri arr listesine ekle
                adapter.notifyDataSetChanged() // Adapter'ı güncelle


            }else{
                Toast.makeText(this,"Plase Select Date!",Toast.LENGTH_LONG).show()
            }
        }



    }


}
