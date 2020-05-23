package com.example.task_qualwebs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task_qualwebs.R
import kotlinx.android.synthetic.main.activity_caht.*
import kotlinx.android.synthetic.main.activity_main.*

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caht)

        chatAsUser1.setOnClickListener {
            var i = Intent(this, MessageActivity::class.java)
            i.putExtra("data","User1")

            startActivity(i)
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_left
            )
        }

        chatAsUser2.setOnClickListener {
            var i = Intent(this, MessageActivity::class.java)
            i.putExtra("data","User2")

            startActivity(i)
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_left
            )
        }

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_right
        )
    }
}
