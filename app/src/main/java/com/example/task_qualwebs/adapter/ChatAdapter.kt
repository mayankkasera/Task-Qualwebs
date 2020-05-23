package com.example.task_qualwebs.adapter

import android.R.id
import android.content.Context
import android.graphics.Color
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_qualwebs.R
import com.example.task_qualwebs.model.Chat
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.message.view.*
import java.util.*


class ChatAdapter(options: FirebaseRecyclerOptions<Chat>,var reciver: String ,var sender: String , context: Context) : FirebaseRecyclerAdapter<Chat, ChatAdapter.ChatViewholder>(options) {


    class ChatViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chat= itemView.chat
        val schat = itemView.schat
        val flayout= itemView.flayout
        val slayout= itemView.slayout
        val timestamp= itemView.timestamp
        val stime= itemView.stimestamp
        val grey= itemView.grey
        val blue= itemView.blue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.message, parent, false)
        return ChatViewholder(v)
    }

    override fun onBindViewHolder(holder: ChatViewholder, position: Int, model: Chat) {
        if (model.type.equals("send")){
            if (model.status.equals("read")){
                holder.blue.setVisibility(View.VISIBLE);
                holder.grey.setVisibility(View.GONE);
            }
            else {
                holder.grey.setVisibility(View.VISIBLE);
                holder.blue.setVisibility(View.GONE);
            }
        }

        if (!model.type.equals("send")) {
            holder.chat.setBackgroundColor(Color.WHITE);
            holder.chat.setText(model.message);
            holder.stime.setText(getDate(model.time!!));
            holder.slayout.setVisibility(View.GONE);
        } else {
            holder.chat.setBackgroundColor(Color.WHITE);
            holder.schat.setText(model.message);
            holder.timestamp.setText(getDate(model.time!!));
            holder.flayout.setVisibility(View.GONE);
        }

        if (model.type.equals("recive") && model.status.equals("unread")) {
            val map: MutableMap<String, Any> = HashMap()
            map["status"] = "read"

            FirebaseDatabase.getInstance().getReference().child("Message")
                .child(reciver).child(sender)
                .child(model.sender!!)
                .updateChildren(map)
                .addOnCompleteListener{

                    FirebaseDatabase.getInstance().reference.child("Message")
                        .child(sender).child(reciver)
                        .child(getRef(position).key!!)
                        .updateChildren(map)
                        .addOnCompleteListener { Log.i("nmfdbdm", "onComplete: ") }
                }

        }

    }

     fun getDate(time: Long): String? {
        val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
        cal.setTimeInMillis(time)
        return DateFormat.format("HH:mm", cal).toString()
    }

}