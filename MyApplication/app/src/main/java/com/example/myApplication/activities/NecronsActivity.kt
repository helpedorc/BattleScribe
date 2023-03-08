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


class NecronsActivity : AppCompatActivity() {

    private lateinit var addAptek: Button
    private lateinit var minusAptek: Button
    private lateinit var addBase: Button
    private lateinit var minusBase: Button
    private lateinit var addGeneral: Button
    private lateinit var minusGeneral: Button
    private lateinit var saveButton: Button
    private lateinit var addImmortal: Button
    private lateinit var visualizzaSalvataggi: Button
    private lateinit var minusImmortal: Button
    private lateinit var numPunti: TextView
    private lateinit var numPuntiB: TextView
    private lateinit var numPuntiG: TextView
    private lateinit var numPuntiS: TextView
    private lateinit var numPoint: TextView

    private lateinit var pointsSave: Points

    private val parser = PreferenceParser(Points::class.java, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_necrons)


        var contatorePuntiBase = 0
        var contatoreAPtek = 0
        var contatoreGenerale = 0
        var contatoreImmortal = 0
        var punti = 0
        initialize()

        setSupportActionBar(findViewById(R.id.toolBar))

        var mpAdd: MediaPlayer = MediaPlayer.create(this, R.raw.add)
        var mpMinus: MediaPlayer = MediaPlayer.create(this, R.raw.minus)
        var mpSave: MediaPlayer = MediaPlayer.create(this, R.raw.save)
        pointsSave = parser.loadPreference()!!

        numPuntiB.text = contatoreAPtek.toString()
        numPunti.text = contatorePuntiBase.toString()
        numPuntiG.text = contatoreGenerale.toString()
        numPuntiS.text = contatoreImmortal.toString()
        numPoint.text = punti.toString()


        addAptek.setOnClickListener() {
            contatoreAPtek += 3
            numPuntiB.text = contatoreAPtek.toString()
            punti += 200
            numPoint.text = punti.toString()
            mpAdd.start()
        }

        minusAptek.setOnClickListener() {
            contatoreAPtek -= 3
            numPuntiB.text = contatoreAPtek.toString()
            if (contatoreAPtek < 0) {
                contatoreAPtek = 0
                numPuntiB.text = contatoreAPtek.toString()
            }
            if (contatoreAPtek == 0) {
                punti -= 0
                numPoint.text = contatoreAPtek.toString()
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

        addImmortal.setOnClickListener() {
            contatoreImmortal += 5
            numPuntiS.text = contatoreImmortal.toString()
            punti += 50
            numPoint.text = punti.toString()
            mpAdd.start()
        }

        minusImmortal.setOnClickListener() {
            contatoreImmortal -= 5
            numPuntiS.text = contatoreImmortal.toString()
            if (contatoreImmortal < 0) {
                contatoreImmortal = 0
                numPuntiS.text = contatoreImmortal.toString()
            }
            if (contatoreImmortal == 0) {
                punti -= 0
                numPoint.text = contatoreImmortal.toString()
            } else {
                punti -= 50
            }
            if (punti < 0) {
                punti = 0
                numPoint.text = punti.toString()
            }
            numPoint.text = punti.toString()
            mpMinus.start()

            if (contatoreAPtek == 0 && contatoreImmortal == 0 && contatorePuntiBase == 0 && contatoreGenerale == 0) {
                punti = 0
                numPoint.text = punti.toString()
            }
        }



        saveButton.setOnClickListener() {
            mpSave.start()
            val puntiSalvati = Partita()
            val nomeArmata = "Necrons"
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
        addAptek = findViewById(R.id.Plus2)
        minusAptek = findViewById(R.id.Minus2)
        addBase = findViewById(R.id.Plus1)
        minusBase = findViewById(R.id.Minus1)
        addGeneral = findViewById(R.id.Plus3)
        minusGeneral = findViewById(R.id.Minus3)
        addImmortal = findViewById(R.id.Plus4)
        minusImmortal = findViewById(R.id.Minus4)
        numPunti = findViewById(R.id.Punti)
        saveButton = findViewById(R.id.button)
        visualizzaSalvataggi = findViewById(R.id.buttonSalvataggi)
        numPuntiB = findViewById(R.id.Punti1)
        numPuntiG = findViewById(R.id.Punti2)
        numPuntiS = findViewById(R.id.Punti3)
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