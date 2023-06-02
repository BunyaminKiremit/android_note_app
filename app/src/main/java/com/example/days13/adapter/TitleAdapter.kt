package com.example.days13.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.days13.R
import com.example.days13.models.Note

class TitleAdapter(context: Context, notes: List<Note>) : ArrayAdapter<Note>(context, 0, notes) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.note_list, parent, false)
        }

        val note = getItem(position)

        val titleView = view!!.findViewById<TextView>(R.id.note_title)
        titleView.text = note?.title

        return view
    }
}