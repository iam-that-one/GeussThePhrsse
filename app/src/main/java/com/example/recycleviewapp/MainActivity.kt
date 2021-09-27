package com.example.recycleviewapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    lateinit var add : Button
    lateinit var txt : EditText
    lateinit var lbl : TextView
    lateinit var  lbl1 : TextView
    lateinit var clear : Button
    var message = ""
     var tarace = listOf("HI THERE", "YOU ARE WELCOME","KOTLIN APPLICATION", "HI LOVLY")
    var messages = mutableListOf<String>()
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById(R.id.editTextTextPersonName3)

        add = findViewById(R.id.button2)
        lbl1 = findViewById(R.id.textView)
        clear = findViewById(R.id.button1)
        clear.alpha = 1F
        val myRV = findViewById<RecyclerView>(R.id.rvMain)
        myRV.adapter = RecycleViewAddapter(messages)
        myRV.layoutManager = LinearLayoutManager(this)
        var ans = ""
        var rand = Random.nextInt(tarace.size-1)
        var randPhrase = tarace[rand]
        var hint = ""
        for (i in 0..randPhrase.length-1){
            if(randPhrase[i] != ' '){
                hint = hint + "*"
            }else{
                hint = hint + randPhrase[i]
            }
        }
        lbl1.text = "Guess the correct phrase [${hint}]"
        clear.setOnClickListener{
            lbl1.text = "Guess the correct phrase [${hint}]"
            clear.alpha = 1F
            messages.clear()
            myRV.adapter?.notifyDataSetChanged()
        }
        add.setOnClickListener {
                ans = txt.text.toString()
                rand = Random.nextInt(tarace.size - 1)
                // var randomPhrase = tarace[rand]
                if (ans == randPhrase) {
                    lbl1.text = "Guess the correct phrase [Unlocked] [${ans}]"
                    clear.alpha = 1F
                    messages.add("YOU GOT THE PHRASE")
                } else {
                    var str = ""
                    for (i in 0..randPhrase.length - 1) {
                        if (randPhrase[i] == ans[i]) {
                            str = str + randPhrase[i]
                        } else {
                            str = str + "*"
                        }
                        if (ans.length > randPhrase.length || ans.length < randPhrase.length) {
                            str = "Miss match length, try again ${hint}"
                            break
                        }
                    }
                    lbl1.text = str
                }
                myRV.adapter?.notifyDataSetChanged()
                txt.text.clear()

        }
    }
}