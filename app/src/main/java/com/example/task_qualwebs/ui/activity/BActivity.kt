package com.example.task_qualwebs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task_qualwebs.R

class BActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_left
        )
    }
}
