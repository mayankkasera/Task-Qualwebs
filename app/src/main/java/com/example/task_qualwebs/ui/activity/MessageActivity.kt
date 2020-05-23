package com.example.task_qualwebs.ui.activity


import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.task_qualwebs.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.android.synthetic.main.activity_message.*


class MessageActivity : AppCompatActivity() {

    lateinit var message: DatabaseReference
    lateinit var sender :String
    lateinit var reciver :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        init()



        send.setOnClickListener {
            sendMsg()
        }


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

    private fun sendMsg() {
        val map: MutableMap<String, Any> = HashMap()
        map["message"] = messageEdt.text.toString()
        map["time"] = ServerValue.TIMESTAMP
        map["status"] = "unread"
        map["type"] = "send"

        message.child(sender).child(reciver).push().setValue(map)
            .addOnCompleteListener{
                val map: MutableMap<String, Any> = HashMap()
                map["message"] = messageEdt.text.toString()
                map["time"] = ServerValue.TIMESTAMP
                map["status"] = "unread"
                map["sender"] = sender
                map["type"] = "recive"


                message.child(reciver).child(sender).push().setValue(map)
                    .addOnCompleteListener {
                        messageEdt.setText("")
                    }
            }

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_left
        )
    }
}
