package com.example.task_qualwebs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task_qualwebs.R

class AActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_right
        )
    }
}
