package com.example.thehighbrow.visitormanagement

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import org.w3c.dom.Text

import java.util.ArrayList

internal class VisitorAdapter(var visitors: ArrayList<Visitor>) : RecyclerView.Adapter<VisitorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): VisitorAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.user_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: VisitorAdapter.ViewHolder, i: Int) {

        viewHolder.name.text = visitors[i].getName()
        viewHolder.contact.text = visitors[i].getContact()
        viewHolder.host.text = visitors[i].getHost()
        viewHolder.time.text = visitors[i].getTime()
        viewHolder.date.text = visitors[i].getDate()
        viewHolder.companion.text = visitors[i].getCompanion()
        viewHolder.outtime.text = visitors[i].getOuttime()

        //      String purl = visitors.get(i).getPhotoUrl();
        /*   if (Uri.parse(purl)!=null){
            Uri puri = Uri.parse(purl);
            Picasso.get().load(puri).into(viewHolder.photo);
        }
        else{*/
     //   val imageuri = Uri.parse(visitors[i].getPhotoUrl())
        Picasso.get().load(visitors[i].getPhotoUrl()).into(viewHolder.photo)
        //    viewHolder.photo.setImageURI(imageuri);
        //     }


    }

    override fun getItemCount(): Int {
        return visitors.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView
        var contact: TextView
        var host: TextView
        var photo: ImageView
        var time: TextView
        var date:TextView
        var companion:TextView
        var outtime:TextView

        init {
            name = itemView.findViewById(R.id.namefield)
            contact = itemView.findViewById(R.id.contactfield)
            host = itemView.findViewById(R.id.hostfield)
            photo = itemView.findViewById(R.id.visitorphoto)
            time = itemView.findViewById(R.id.timefield)
            date = itemView.findViewById(R.id.datefield)
            companion = itemView.findViewById(R.id.companionfield)
            outtime = itemView.findViewById(R.id.outtimefield)


        }
    }

}
