package com.example.thehighbrow.visitormanagement

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.util.ArrayList

internal class LeadAdapter (var lead: ArrayList<Lead>) : RecyclerView.Adapter<LeadAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): LeadAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.day_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: LeadAdapter.ViewHolder, i: Int) {

        viewHolder.name.text = lead[i].getName()
        viewHolder.contact.text = lead[i].getContact()
        viewHolder.email.text = lead[i].getEmail()
        viewHolder.reach.text = lead[i].getReach()
        viewHolder.time.text = lead[i].getTime()
        viewHolder.date.text = lead[i].getDate()
        Picasso.get().load(lead[i].getPhotoUrl()).into(viewHolder.photo)



    }

    override fun getItemCount(): Int {
        return lead.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView
        var contact: TextView
        var email: TextView
        var photo: ImageView
        var reach: TextView
        var time: TextView
        var date: TextView

        init {
            name = itemView.findViewById(R.id.namefield)
            contact = itemView.findViewById(R.id.contactfield)
            email = itemView.findViewById(R.id.hostfield)
            photo = itemView.findViewById(R.id.visitorphoto)
            reach = itemView.findViewById(R.id.reachfield)
            time = itemView.findViewById(R.id.timefield)
            date = itemView.findViewById(R.id.datefield)


        }
    }

}