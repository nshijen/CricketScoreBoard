package com.shijen.cricketscoreboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.shijen.cricketscoreboard.game.FragmentGame
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var game:Fragment =
            FragmentGame.getInstance();
        supportFragmentManager.beginTransaction().add(container.id,game,"Game").commit()
    }
}
