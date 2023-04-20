package com.example.dbase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var buttonadd:Button
    lateinit var buttonprint:Button
    lateinit var name_txt:EditText
    lateinit var age_txt:EditText

    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonadd = findViewById(R.id.addName)
        buttonprint = findViewById(R.id.printName)
        name_txt=findViewById(R.id.enterName)
        age_txt=findViewById(R.id.enterAge)


        buttonadd.setOnClickListener {
            val db = DBHelper(this, null)

            val name = name_txt.text.toString()
            val age = age_txt.text.toString()

            db.addName(name, age)

            Toast.makeText(this, name + "added to database", Toast.LENGTH_LONG).show()

            name_txt.text.clear()
            age_txt.text.clear()
        }

        buttonprint.setOnClickListener {

            val db = DBHelper(this, null)

            val cursor = db.getName()

            cursor!!.moveToFirst()
            name_txt.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL)) + "\n")
            age_txt.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")


            while (cursor.moveToNext()){
                name_txt.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL))+"\n")
                age_txt.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL))+"\n")
            }


        cursor.close()


    }
    }
}