package com.example.myvaccines

enum class Condizioni (val nameStr: String){
    GRAVIDANZE("Gravidanze"),
    ALLERGIE(nameStr = "Allergie"),
    PATOLOGIE_CARDIACHE(nameStr = "Patologie Cardiaache"),
    PATOLOGIE_RESPIRATORIE("Patologie Respiratorie"),
    PATOLOGIE_METABOLICHE("Patologie Metaboliche"),
    IMMUNODEPRESSIONE("Immunodepressione");

    fun getNameString(): String{
        return this.nameStr;
    }
}