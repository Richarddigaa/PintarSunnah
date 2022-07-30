package com.richard.pintarsunnah

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.richard.pintarsunnah.databinding.ActivityHasilExamBinding

class HasilExamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHasilExamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHasilExamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val benar = intent.getIntExtra("benar", 0)
        val salah = intent.getIntExtra("salah", 0)
        val hasil = intent.getDoubleExtra("hasil", 0.0)

        val angkaSignifikan = 2
        val temp = Math.pow(10.0, angkaSignifikan.toDouble())
        val y = Math.round(hasil * temp).toDouble() / temp

        binding.apply {
            TvHasil.setText("Jawaban Benar : $benar"+"\nJawaban Salah : $salah")
            TvNilai.setText(y.toString())

            if (benar >= 10) {
                BtnUlangi.setText("Cetak Sertifikat")
                BtnUlangi.setOnClickListener {
                    
                }
            } else {
                BtnUlangi.setText("Ulangi Exam")
                BtnUlangi.setOnClickListener {
                    finish()
                    startActivity(Intent(this@HasilExamActivity, TestExamActivity::class.java))
                }
            }
        }
    }
}