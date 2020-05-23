package com.example.task_qualwebs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.task_qualwebs.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FirebaseDatabase.getInstance().reference.child("dnbvdf").setValue("dfjbvhjd")
            .addOnCanceledListener{

            }

        goToMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            overridePendingTransition(
                    R.anim.slide_in_up,
                    R.anim.slide_out_up
            )
        }

        goToLeft.setOnClickListener {
            startActivity(Intent(this, AActivity::class.java))
            overridePendingTransition(
                    R.anim.slide_in_left,
                    R.anim.slide_out_left
            )

        }

        goToRight.setOnClickListener {
            startActivity(Intent(this, BActivity::class.java))
            overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_right
            )
        }
    }
}
