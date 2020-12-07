package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val shareprefile = "KotlinSharedPreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputid = findViewById<EditText>(R.id.editid)
        val inputname = findViewById<EditText>(R.id.editname)
        val outputid = findViewById<TextView>(R.id.showid)
        val outputname = findViewById<TextView>(R.id.showname)

        val btnsave = findViewById<Button>(R.id.save)
        val btnview = findViewById<Button>(R.id.view)
        val btnclear = findViewById<Button>(R.id.clear)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(shareprefile,Context.MODE_PRIVATE)
        btnsave.setOnClickListener(View.OnClickListener {
            val id:Int = Integer.parseInt(inputid.text.toString())
            val name:String = inputname.text.toString()
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key",id)
            editor.putString("name_key",name)
            editor.apply()
            editor.commit()
        })

        btnview.setOnClickListener{
            val sharedidvalue = sharedPreferences.getInt("id_key",0)
            val sharednamevalue = sharedPreferences.getString("name_key","defaultname")
            if (sharedidvalue.equals(0)&&sharednamevalue.equals("defaultname")){
                outputname.setText("default name:${sharednamevalue}")
                outputid.setText("default id:${sharedidvalue.toString()}")
            }else{
                outputname.setText(sharednamevalue).toString()
                outputid.setText(sharedidvalue.toString())
            }
        }

        btnclear.setOnClickListener(View.OnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            outputname.setText("").toString()
            outputid.setText("".toString())
        })
    }
}