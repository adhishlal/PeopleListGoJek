package com.gopay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gopay.network.responses.Results
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PeopleListAdapter(private var peopleList: List<Results>) :
    RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder>() {

    @Inject
    lateinit var picasso: Picasso

    class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personImage = itemView.findViewById<ImageView>(R.id.personImage)
        val personName = itemView.findViewById<TextView>(R.id.personName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_people, parent, false)
        return PeopleViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val people = peopleList[position]

        holder.personName.text = people.name

        Picasso.get()
            .load("https://i.pravatar.cc/")
            .resize(50, 50)
            .centerCrop()
            .into(holder.personImage)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }
}