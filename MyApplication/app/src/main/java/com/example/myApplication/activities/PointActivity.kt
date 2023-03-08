package com.example.myApplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.myApplication.model.Points
import com.example.myapplication.R
import com.google.gson.Gson

class PointActivity : AppCompatActivity() {
    private lateinit var storico: Points
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point)

        setSupportActionBar(findViewById(R.id.toolBar))

        val extras = intent.extras?.getString("HISTORY")
        storico = Gson().fromJson(extras, Points::class.java)
        inizializzazione()
    }

    private fun inizializzazione() {
        listView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this, R.layout.single_row, storico.listaPunti)
        listView.adapter = adapter
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
            R.id.back -> finish()
        }

        return false
    }
}