package com.example.nluc25_sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.nluc25_sqlite.model.Student


class DatabaseHelper(context:Context):SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION) {
    lateinit var db: SQLiteDatabase //لاتمكن من خلاله من استخدام دوال الاضافة والحذف والتعديل

    init {
     db = this.writableDatabase
    }
    companion object{
        const val DB_NAME ="universitydb"
        const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(Student.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${Student.TABLE_NAME}")
        onCreate(db)
    }

    //**************************************** insert new Student **********************************************
    fun insertStudent(name:String, average:Double):Boolean {
        val cv = ContentValues()
        cv.put(Student.COL_NAME, name)
        cv.put(Student.COL_AVG, average)
        return db.insert(Student.TABLE_NAME, null, cv) > 0
    }

    //**************************************** Get Student عرض البيانات المضافة***********************************
    fun getAllStudent():ArrayList<Student>{
        var students = ArrayList<Student>()
        val carser = db.rawQuery("SELECT * FROM ${Student.TABLE_NAME} order by ${Student.COL_ID} ",null)

            carser.moveToFirst()
            while(!carser.isAfterLast){
                val s = Student(carser.getInt(0),carser.getString(1),carser.getDouble(2))
                students.add(s)
                carser.moveToNext()
            }
        carser.close()
        return students
    }

    //**************************************** Delete Student حذف البيانات المضافة *********************************
    fun deleteStudent(id:Int):Boolean{
        return db.delete(Student.TABLE_NAME,"${Student.COL_ID}=$id",null)>0
    }

    //****************************************  Update Student  تحديث البيانات المضافة  *********************************
    fun updateStudent(oldId:Int,name:String,average:Double):Boolean{
        val cv = ContentValues()
        cv.put(Student.COL_NAME,name)
        cv.put(Student.COL_AVG, average)
        return db.update(Student.TABLE_NAME,cv,"${Student.COL_ID}==$oldId",null)>0
    }


}