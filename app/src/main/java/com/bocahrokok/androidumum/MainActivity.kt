package com.bocahrokok.androidumum

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.bocahrokok.androidumum.data.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set to fullscreen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        //onCLick listener button start
        btn_start.setOnClickListener {

            //if name is empty; toas
            if(et_name.text.toString().isEmpty()){
                Toast.makeText(this,"No Name, please give your name", Toast.LENGTH_SHORT).show()
            } else {
                //kalo ga empty add intent;
                val intent = Intent(this , QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())
                //start the acvity;
                startActivity(intent)
                finish()
            }
        }
    }


}