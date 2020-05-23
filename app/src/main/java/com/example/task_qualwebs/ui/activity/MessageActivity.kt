package com.example.task_qualwebs.ui.activity


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.example.task_qualwebs.R
import com.example.task_qualwebs.adapter.ChatAdapter
import com.example.task_qualwebs.model.Chat
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.android.synthetic.main.activity_message.*
import javax.security.auth.login.LoginException


class MessageActivity : AppCompatActivity() {

    lateinit var message: DatabaseReference
    lateinit var sender :String
    lateinit var reciver :String
    lateinit var adapter : ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        init()

        setUpRecyclerView()

        send.setOnClickListener {
            var text = messageEdt.text.toString()
            messageEdt.setText("")
            sendMsg(text)
        }


    }

    private fun setUpRecyclerView() {
        val options = FirebaseRecyclerOptions.Builder<Chat>()
            .setQuery(message.child(sender).child(reciver), Chat::class.java)
            .build()

        adapter = ChatAdapter(options,reciver,sender)
        recyclerview.adapter = adapter

        adapter.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                recyclerview.smoothScrollToPosition(adapter.getItemCount())
            }
        })
    }

    private fun init() {

        message = FirebaseDatabase.getInstance().reference.child("Message")

        if(intent.getStringExtra("data").equals("User1")){
            sender = "User1"
            reciver = "User2"
        }
        else{
            sender = "User2"
            reciver = "User1"
        }
    }

    private fun sendMsg(text: String) {


        val map: MutableMap<String, Any> = HashMap()
        map["message"] = text
        map["time"] = ServerValue.TIMESTAMP
        map["status"] = "unread"
        map["type"] = "send"
        map["sender"] = sender


        var ref =message.child(sender).child(reciver).push()

        ref.setValue(map)
            .addOnCompleteListener{
                val map: MutableMap<String, Any> = HashMap()
                map["message"] = text
                map["time"] = ServerValue.TIMESTAMP
                map["status"] = "unread"
                map["sender"] = ref.key.toString()
                map["type"] = "recive"


                message.child(reciver).child(sender).push().setValue(map)
                    .addOnCompleteListener {

                    }
            }

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_right
        )
    }
}
