package com.example.days13.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.days13.R
import com.example.days13.models.Note

class NoteAdapter(private val context: Context, private val notes: List<Note>) : BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return notes.size
    }

    override fun getItem(position: Int): Any {
        return notes[position]
    }

    override fun getItemId(position: Int): Long {
        return notes[position].nid.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false)
        }

        val note = getItem(position) as Note

        val txtID= view!!.findViewById<TextView>(R.id.txtID)
        val titleView = view!!.findViewById<TextView>(R.id.note_title)
        val detailView = view!!.findViewById<TextView>(R.id.note_detail)
        val dateView = view!!.findViewById<TextView>(R.id.note_date)

        txtID.text=note.nid.toString()
        titleView.text = note.title
        detailView.text = note.detail
        dateView.text = note.date

        return view
    }
}