package com.richard.pintarsunnah

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.richard.pintarsunnah.databinding.ActivityExamBinding

class ExamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExamBinding

    private var pointNo1: Double = 0.0
    private var pointNo2: Double = 0.0
    private var pointNo3: Double = 0.0
    private var pointNo4: Double = 0.0
    private var pointNo5: Double = 0.0
    private var pointNo6: Double = 0.0
    private var pointNo7: Double = 0.0
    private var pointNo8: Double = 0.0
    private var pointNo9: Double = 0.0
    private var pointNo10: Double = 0.0
    private var pointNo11: Double = 0.0
    private var pointNo12: Double = 0.0
    private var pointNo13: Double = 0.0
    private var pointNo14: Double = 0.0
    private var pointNo15: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            BtnSelesai.setOnClickListener {
                if (RbNo1.isClickable || RbNo2.isClickable || RbNo3.isClickable ||
                    RbNo4.isClickable || RbNo5.isClickable || RbNo6.isClickable ||
                    RbNo7.isClickable || RbNo8.isClickable || RbNo9.isClickable ||
                    RbNo11.isClickable || RbNo12.isClickable || RbNo13.isClickable ||
                    RbNo14.isClickable || RbNo15.isClickable) {
                    when {
                        RbB1.isChecked -> pointNo1 = 6.6
                        RbB2.isChecked -> pointNo2 = 6.6
                        RbC3.isChecked -> pointNo3 = 6.6
                        RbD4.isChecked -> pointNo4 = 6.6
                        RbA5.isChecked -> pointNo5 = 6.6
                        RbD6.isChecked -> pointNo6 = 6.6
                        RbC7.isChecked -> pointNo7 = 6.6
                        RbD8.isChecked -> pointNo8 = 6.6
                        RbA9.isChecked -> pointNo9 = 6.6
                        RbC10.isChecked -> pointNo10 = 6.6
                        RbC11.isChecked -> pointNo11 = 6.6
                        RbA12.isChecked -> pointNo12 = 6.6
                        RbB13.isChecked -> pointNo13 = 6.6
                        RbA14.isChecked -> pointNo14 = 6.6
                        RbC15.isChecked -> pointNo15 = 6.6

                        else -> {
                            pointNo1 = 0.0
                            pointNo2 = 0.0
                            pointNo3 = 0.0
                            pointNo4 = 0.0
                            pointNo5 = 0.0
                            pointNo6 = 0.0
                            pointNo7 = 0.0
                            pointNo8 = 0.0
                            pointNo9 = 0.0
                            pointNo10 = 0.0
                            pointNo11 = 0.0
                            pointNo12 = 0.0
                            pointNo13 = 0.0
                            pointNo14 = 0.0
                            pointNo15 = 0.0
                        }
                    }
                } else {
                    Toast.makeText(this@ExamActivity, "Masih ada yang kosong", Toast.LENGTH_SHORT).show()
                }
            }
            val hasil =
                pointNo1 + pointNo2 + pointNo3 + pointNo4 + pointNo5 + pointNo6 + pointNo7 + pointNo8 + pointNo9 + pointNo10 + pointNo11 + pointNo12 + pointNo13 + pointNo14 + pointNo15 + 1

            Log.e(TAG, "hasil: $hasil")
        }
    }
}
