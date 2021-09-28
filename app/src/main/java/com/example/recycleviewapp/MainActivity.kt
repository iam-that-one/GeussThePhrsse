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
    lateinit var guess: Button
    lateinit var txt: EditText
    lateinit var lbl: TextView
    lateinit var lbl1: TextView
    lateinit var clear: Button
    var count = 0

    var message = ""
    val phrases = listOf("HI THERE", "YOU ARE WELCOME", "KOTLIN APPLICATION", "HI LOVLY ONE")
    var rand: Int = 0
    var randPhrase: String = ""
    var messages = mutableListOf<String>()

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById(R.id.editTextTextPersonName3)
        guess = findViewById(R.id.button2)
        lbl1 = findViewById(R.id.textView)
        clear = findViewById(R.id.button1)
        clear.alpha = 1F

        val myRV = findViewById<RecyclerView>(R.id.rvMain)
        myRV.adapter = RecycleViewAddapter(messages)
        myRV.layoutManager = LinearLayoutManager(this)
        var ans = ""
        var hint = ""
        var str = ""
        clear.setOnClickListener {
            str = ""
           hint = ""
            rand = Random.nextInt(phrases.size)
            randPhrase = phrases[rand]
            for (i in 0..randPhrase.length - 1) {
                if (randPhrase[i] != ' ') {
                    hint = hint + "*"
                } else {
                    hint = hint + " "
                }
            }

            lbl1.text = hint

        }
        guess.setOnClickListener {
            ans = txt.text.toString()

            if (ans == randPhrase) {
                lbl1.text = "You guessed                                                                                                                                      the correct phrase [Unlocked] [${ans}]"
                messages.add("YOU GOT THE PHRASE")
                hint = ""
                count++
            }
            else{
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
               // hint = ""
            }
            myRV.adapter?.notifyDataSetChanged()
            txt.text.clear()

        }
    }
}