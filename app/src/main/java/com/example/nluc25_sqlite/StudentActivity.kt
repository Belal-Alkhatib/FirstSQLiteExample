package com.example.nluc25_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nluc22_recyclerview.Adapters.StudentAdapter
import com.example.nluc25_sqlite.databinding.ActivityStudentBinding
import com.example.nluc25_sqlite.db.DatabaseHelper

class StudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val dataAccess = DatabaseHelper(this)
        val data = dataAccess.getAllStudent()

        binding.rvStudent.layoutManager = LinearLayoutManager(this)
        val studentAdapter = StudentAdapter(this,data)
        binding.rvStudent.adapter = studentAdapter
    }
}