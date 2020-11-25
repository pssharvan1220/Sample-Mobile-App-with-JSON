package com.mobile.se_2017_037

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mobile.se_2017_037.dataclass.MyData

class ListAdapter(val context: Context, val list:ArrayList<MyData>):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //  TODO("Not yet implemented")

        val view: View = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false)
        val userid = view.findViewById<TextView>(R.id.userid)
        val id = view.findViewById<TextView>(R.id.id)
        val title = view.findViewById<TextView>(R.id.title)
        val body = view.findViewById<TextView>(R.id.body)

        userid.text = list[position].userId.toString()
        id.text = list[position].id.toString()
        title.text = list[position].title.toString()
        body.text = list[position].body.toString()

        return view
    }

    override fun getItem(position: Int): Any {
        //  TODO("Not yet implemented")

        return position
    }

    override fun getItemId(position: Int): Long {
        //  TODO("Not yet implemented")

        return position.toLong()
    }

    override fun getCount(): Int {
        // TODO("Not yet implemented")

        return list.size
    }
}

