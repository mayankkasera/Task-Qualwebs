package com.example.task_qualwebs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_down,
            R.anim.slide_out_down
        )
    }

}
