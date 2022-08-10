package com.example.nluc25_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nluc25_sqlite.databinding.ActivityMainBinding
import com.example.nluc25_sqlite.db.DatabaseHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {

            if(binding.tvName.text.isEmpty() || binding.tvAverage.text.isEmpty()){
                Toast.makeText(applicationContext,"Fill Fields",Toast.LENGTH_SHORT).show()
            }else{
                val db = DatabaseHelper(this)
                if(db.insertStudent(binding.tvName.text.toString(),binding.tvAverage.text.toString().toDouble()))
                    Toast.makeText(applicationContext,"Successfully Add",Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(applicationContext,"Failed Add",Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnGet.setOnClickListener {
            val i = Intent(applicationContext , StudentActivity::class.java)
            startActivity(i)
        }


    }
}