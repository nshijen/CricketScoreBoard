package com.shijen.cricketscoreboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var scoreboard:Fragment = FragmentScoreboard();
        supportFragmentManager.beginTransaction().add(container.id,scoreboard,"Scoreboard").commit()
    }
}
