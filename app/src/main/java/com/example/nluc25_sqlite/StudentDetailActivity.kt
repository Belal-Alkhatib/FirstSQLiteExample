package com.example.nluc25_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nluc25_sqlite.model.Student
import kotlinx.android.synthetic.main.activity_student_detail.*

class StudentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        val s = intent.getParcelableExtra<Student>("data")
       // etResult.text = s.toString()

    }
}