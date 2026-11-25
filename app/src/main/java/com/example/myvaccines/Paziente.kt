package com.example.myvaccines

data class Paziente(
    val eta: Int,
    val terapiaBiologica: List<Terapie>,
    val condizioniCliniche: List<Condizioni>
)
