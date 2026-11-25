package com.example.myvaccines

enum class Condizioni (val nameStr: String){
    GRAVIDANZA("Gravidanza"),
    ALLERGIE(nameStr = "Allergie"),
    PATOLOGIE_CARDIACHE(nameStr = "Patologie Cardiache"),
    PATOLOGIE_RESPIRATORIE("Patologie Respiratorie"),
    PATOLOGIE_METABOLICHE("Patologie Metaboliche"),
    IMMUNODEPRESSIONE("Immunodepressione");

    fun getNameString(): String{
        return this.nameStr;
    }
}