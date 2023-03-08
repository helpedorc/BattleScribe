package com.example.myApplication.model


class Points(var contatorePuntiBase: Int = 0) {

    var listaPunti = mutableListOf<Partita>()

}

class Partita() {
    var punti: Int = 0
    var nome: String = ""

    override fun toString(): String {
        return "I punti spesi per l'armata: " + nome + " sono " + punti
    }
}