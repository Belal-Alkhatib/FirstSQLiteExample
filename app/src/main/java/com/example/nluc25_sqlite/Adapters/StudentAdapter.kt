package com.example.nluc22_recyclerview.Adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nluc25_sqlite.StudentDetailActivity
import com.example.nluc25_sqlite.databinding.StudentItemBinding
import com.example.nluc25_sqlite.db.DatabaseHelper
import com.example.nluc25_sqlite.model.Student

class StudentAdapter(var activity:Activity , var data:ArrayList<Student>): RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: StudentItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = StudentItemBinding.inflate(activity.layoutInflater , parent , false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.txtId.text = data[position].id.toString()
        holder.binding.txtName.text = data[position].name
        holder.binding.txtAvg.text = data[position].average.toString()

        holder.binding.btnDelet.setOnClickListener {

            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete Student")
            builder.setMessage("Are You Wont to Delete ?")

            builder.setPositiveButton("yes"){ _,_ ->
                val db = DatabaseHelper(activity)
                if(db.deleteStudent(data[position].id)){
                    data.removeAt(position)
                    notifyDataSetChanged()
                }else
                    Toast.makeText(activity,"Delete Failed",Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("No"){ d ,_ ->
                d.dismiss()
            }
            builder.create().show()
    }
        holder.binding.btnDitels.setOnClickListener {
             val i = Intent(activity ,StudentDetailActivity::class.java)
             i.putExtra("data", data[position])
             activity.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}