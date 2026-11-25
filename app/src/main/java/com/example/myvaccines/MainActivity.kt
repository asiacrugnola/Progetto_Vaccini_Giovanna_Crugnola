package com.example.myvaccines

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etEta: EditText
    private lateinit var tvTerapie: TextView
    private lateinit var tvCondizioni: TextView
    private lateinit var btnCalcola: Button

    // Stato delle selezioni per Terapie
    private val tutteTerapie = Terapie.entries.toTypedArray()
    private val terapieSelezionateFlags = BooleanArray(tutteTerapie.size)

    // Stato delle selezioni per Condizioni
    private val tutteCondizioni = Condizioni.entries.toTypedArray()
    private val condizioniSelezionateFlags = BooleanArray(tutteCondizioni.size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEta = findViewById(R.id.etEta)
        tvTerapie = findViewById(R.id.tvTerapie)
        tvCondizioni = findViewById(R.id.tvCondizioni)
        btnCalcola = findViewById(R.id.btnCalcola)

        tvTerapie.setOnClickListener {
            mostraDialogMultipla(
                titolo = "Seleziona terapia biologica",
                etichette = tutteTerapie.map { it.getNameString() }.toTypedArray(),
                flags = terapieSelezionateFlags,
                onConferma = { aggiornaTestoTerapie() }
            )
        }

        tvCondizioni.setOnClickListener {
            mostraDialogMultipla(
                titolo = "Seleziona condizioni cliniche",
                etichette = tutteCondizioni.map { it.getNameString() }.toTypedArray(),
                flags = condizioniSelezionateFlags,
                onConferma = { aggiornaTestoCondizioni() }
            )
        }

        btnCalcola.setOnClickListener {
            val etaTesto = etEta.text.toString().trim()
            val eta = etaTesto.toIntOrNull()

            if (eta == null || eta < 0) {
                etEta.error = "Inserisci un'età valida"
                return@setOnClickListener
            }

            val listaTerapie = tutteTerapie.filterIndexed { index, _ ->
                terapieSelezionateFlags[index]
            }

            val listaCondizioni = tutteCondizioni.filterIndexed { index, _ ->
                condizioniSelezionateFlags[index]
            }

            val paziente = Paziente(
                eta = eta,
                terapiaBiologica = listaTerapie,
                condizioniCliniche = listaCondizioni
            )

            // Qui puoi passare 'paziente' alla logica di calcolo raccomandazioni
            Toast.makeText(
                this,
                "Età: ${paziente.eta}, Terapie: ${paziente.terapiaBiologica.size}, Condizioni: ${paziente.condizioniCliniche.size}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun mostraDialogMultipla(
        titolo: String,
        etichette: Array<String>,
        flags: BooleanArray,
        onConferma: () -> Unit
    ) {
        // Copia temporanea per gestire correttamente "Annulla"
        val copiaFlags = flags.copyOf()

        AlertDialog.Builder(this)
            .setTitle(titolo)
            .setMultiChoiceItems(etichette, copiaFlags) { _, which, isChecked ->
                copiaFlags[which] = isChecked
            }
            .setPositiveButton("OK") { _, _ ->
                System.arraycopy(copiaFlags, 0, flags, 0, flags.size)
                onConferma()
            }
            .setNegativeButton("Annulla", null)
            .show()
    }

    private fun aggiornaTestoTerapie() {
        val selezionate = tutteTerapie
            .filterIndexed { index, _ -> terapieSelezionateFlags[index] }
            .joinToString(", ") { it.getNameString() }

        tvTerapie.text = selezionate // Se vuoto, mostra automaticamente l'hint dell'XML
    }

    private fun aggiornaTestoCondizioni() {
        val selezionate = tutteCondizioni
            .filterIndexed { index, _ -> condizioniSelezionateFlags[index] }
            .joinToString(", ") { it.getNameString() }

        tvCondizioni.text = selezionate // Se vuoto, mostra automaticamente l'hint dell'XML
    }
}