package com.example.myvaccines

enum class Terapie (val nameStr: String){
    ANTITNF("anti-TNF"),
    ANTIIL17("anti-IL17"),
    ANTIIL23("anti-IL23"),
    ANTIIL4("anti-IL4"),
    ANTIIL13("anti-IL13"),
    JAKINIBITORI("JAK Inibitori");

    fun getNameString(): String{
        return this.nameStr;
    }
}