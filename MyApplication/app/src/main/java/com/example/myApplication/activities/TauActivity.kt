package com.example.myApplication.activities

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myApplication.model.Partita
import com.example.myApplication.model.Points
import com.example.myApplication.utilities.PreferenceParser
import com.example.myapplication.R
import com.google.gson.Gson


class TauActivity : AppCompatActivity() {
    private lateinit var addCrisis: Button
    private lateinit var minusCrisis: Button
    private lateinit var addBase: Button
    private lateinit var minusBase: Button
    private lateinit var addGeneral: Button
    private lateinit var minusGeneral: Button
    private lateinit var addDrone: Button
    private lateinit var minusDrone: Button
    private lateinit var numPunti: TextView
    private lateinit var numPuntiR: TextView
    private lateinit var numPuntiG: TextView
    private lateinit var numPuntiD: TextView
    private lateinit var numPoint: TextView

    private lateinit var saveButton: Button
    private lateinit var visualizzaSalvataggi: Button
    private lateinit var pointsSave: Points

    private val parser = PreferenceParser(Points::class.java, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tau)
        var contatorePuntiBase = 0
        var contatoreRobot = 0
        var contatoreGenerale = 0
        var contatoreDroni = 0
        var punti = 0
        initialize()

        var mpAdd: MediaPlayer = MediaPlayer.create(this, R.raw.add)
        var mpMinus: MediaPlayer = MediaPlayer.create(this, R.raw.minus)
        var mpSave: MediaPlayer = MediaPlayer.create(this, R.raw.save)

        setSupportActionBar(findViewById(R.id.toolBar))

        pointsSave = parser.loadPreference()!!

        numPuntiR.text = contatoreRobot.toString()
        numPunti.text = contatorePuntiBase.toString()
        numPuntiG.text = contatoreGenerale.toString()
        numPuntiD.text = contatoreDroni.toString()
        numPoint.text = punti.toString()

        addCrisis.setOnClickListener() {
            contatoreRobot += 3
            numPuntiR.text = contatoreRobot.toString()
            punti += 200
            numPoint.text = punti.toString()
            mpAdd.start()
        }

        minusCrisis.setOnClickListener() {
            contatoreRobot -= 3
            numPuntiR.text = contatoreRobot.toString()
            if (contatoreRobot < 0) {
                contatoreRobot = 0
                numPuntiR.text = contatoreRobot.toString()
            }
            if (contatoreRobot == 0) {
                punti -= 0
                numPoint.text = contatoreRobot.toString()
            } else {
                punti -= 200
            }
            if (punti < 0) {
                punti = 0
                numPoint.text = punti.toString()
            }
            numPoint.text = punti.toString()
            mpMinus.start()
        }

        addBase.setOnClickListener() {
            contatorePuntiBase++
            numPunti.text = contatorePuntiBase.toString()
            punti += 100
            numPoint.text = punti.toString()
            mpAdd.start()
        }

        minusBase.setOnClickListener() {
            contatorePuntiBase--
            numPunti.text = contatorePuntiBase.toString()
            if (contatorePuntiBase < 0) {
                contatorePuntiBase = 0
                numPunti.text = contatorePuntiBase.toString()
            }
            if (contatorePuntiBase == 0) {
                punti -= 0
                numPoint.text = contatorePuntiBase.toString()
            } else {
                punti -= 100
            }
            if (punti < 0) {
                punti = 0
                numPoint.text = punti.toString()
            }
            numPoint.text = punti.toString()
            mpMinus.start()
        }

        addGeneral.setOnClickListener() {
            contatoreGenerale += 1
            numPuntiG.text = contatoreGenerale.toString()
            punti += 300
            numPoint.text = punti.toString()
            mpAdd.start()
        }

        minusGeneral.setOnClickListener() {
            contatoreGenerale -= 1
            numPuntiG.text = contatoreGenerale.toString()
            if (contatoreGenerale < 0) {
                contatoreGenerale = 0
                numPuntiG.text = contatoreGenerale.toString()
            }
            if (contatoreGenerale == 0) {
                punti -= 0
                numPoint.text = contatoreGenerale.toString()
            } else {
                punti -= 300
            }
            if (punti < 0) {
                punti = 0
                numPoint.text = punti.toString()
            }
            numPoint.text = punti.toString()
            mpMinus.start()
        }

        addDrone.setOnClickListener() {
            contatoreDroni += 2
            numPuntiD.text = contatoreDroni.toString()
            punti += 50
            numPoint.text = punti.toString()
            mpAdd.start()
        }

        minusDrone.setOnClickListener() {
            contatoreDroni -= 2
            numPuntiD.text = contatoreDroni.toString()
            if (contatoreDroni < 0) {
                contatoreDroni = 0
                numPuntiD.text = contatoreDroni.toString()
            }
            if (contatoreDroni == 0) {
                punti -= 0
                numPoint.text = contatoreDroni.toString()
            } else {
                punti -= 50
            }
            if (punti < 0) {
                punti = 0
                numPoint.text = punti.toString()
            }
            numPoint.text = punti.toString()
            mpMinus.start()
        }

        saveButton.setOnClickListener() {
            mpSave.start()
            val puntiSalvati = Partita()
            val nomeArmata = "Tau"
            puntiSalvati.nome = nomeArmata
            puntiSalvati.punti = punti
            pointsSave.listaPunti.add(puntiSalvati)
            save()
        }

        visualizzaSalvataggi.setOnClickListener() {
            val intent = Intent(this, PointActivity::class.java)
            intent.putExtra("HISTORY", Gson().toJson(pointsSave))
            startActivity(intent)
        }

    }

    private fun initialize() {
        addCrisis = findViewById(R.id.Plus2)
        minusCrisis = findViewById(R.id.Minus2)
        addBase = findViewById(R.id.Plus1)
        minusBase = findViewById(R.id.Minus1)
        addGeneral = findViewById(R.id.Plus3)
        minusGeneral = findViewById(R.id.Minus3)
        addDrone = findViewById(R.id.Plus4)
        minusDrone = findViewById(R.id.Minus4)
        numPunti = findViewById(R.id.Punti)
        saveButton = findViewById(R.id.button)
        visualizzaSalvataggi = findViewById(R.id.buttonSalvataggi)
        numPuntiR = findViewById(R.id.Punti1)
        numPuntiG = findViewById(R.id.Punti2)
        numPuntiD = findViewById(R.id.Punti3)
        numPoint = findViewById(R.id.Punti4)

        parser.initialize()
    }

    private fun save() {
        parser.savePreference(pointsSave)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId

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
