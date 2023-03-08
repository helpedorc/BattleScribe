package com.example.myApplication.activities

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    private lateinit var tau: Button
    private lateinit var orks: Button
    private lateinit var marines: Button
    private lateinit var necrons: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        inizializzazione()

        var mpTau: MediaPlayer = MediaPlayer.create(this, R.raw.tau)
        var mpSpace: MediaPlayer = MediaPlayer.create(this, R.raw.space)
        var mpOrk: MediaPlayer = MediaPlayer.create(this, R.raw.ork)
        var mpNecron: MediaPlayer = MediaPlayer.create(this, R.raw.necron)


        setSupportActionBar(findViewById(R.id.toolBar))

        tau.setOnClickListener {
            mpTau.start()
            val myIntent = Intent(this, TauActivity::class.java)

            startActivity(myIntent)
        }
        orks.setOnClickListener {
            mpOrk.start()
            val myIntent = Intent(this, OrksActivity::class.java)

            startActivity(myIntent)
        }
        marines.setOnClickListener {
            mpSpace.start()
            val myIntent = Intent(this, MarinesActivity::class.java)

            startActivity(myIntent)
        }
        necrons.setOnClickListener {
            mpNecron.start()
            val myIntent = Intent(this, NecronsActivity::class.java)

            startActivity(myIntent)
        }

    }

    private fun inizializzazione() {
        tau = findViewById(R.id.Tau)
        orks = findViewById(R.id.Orks)
        marines = findViewById(R.id.SpaceMarine)
        necrons = findViewById(R.id.Necrons)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId
        when (itemView) {
            R.id.info -> Toast.makeText(
                applicationContext,
                "Application created by Francesco Galgano as a cheap copy of an already exist app named Battel Scribe",
                Toast.LENGTH_LONG
            ).show()
        }

        when (itemView) {
            R.id.version -> Toast.makeText(
                applicationContext,
                "Actual version:1.0",
                Toast.LENGTH_SHORT
            ).show()
        }

        when (itemView) {
            R.id.back -> System.exit(0)
        }

        return false
    }


}