package com.example.kotlin_dynamic_chip_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.txtView)
        val chipGroup: ChipGroup = findViewById(R.id.chipGroup)

        val arrayList = listOf("C", "C++", "C#", "SQL", "Java", "Python", "Flutter", "Jetpack Compose")

        for (s in arrayList) {
            val chip = Chip(this)
            chip.text = s
            chip.isCheckable = true
            chip.id = ViewCompat.generateViewId()

            // Set layout parameters
            val layoutParams = ChipGroup.LayoutParams(
                ChipGroup.LayoutParams.WRAP_CONTENT,
                ChipGroup.LayoutParams.WRAP_CONTENT
            )
            chip.layoutParams = layoutParams

            chip.setOnCheckedChangeListener { _, isChecked ->
                updateSelectedLanguages(chipGroup)
            }

            chipGroup.addView(chip)
        }
    }

    private fun updateSelectedLanguages(chipGroup: ChipGroup) {
        val stringBuilder = StringBuilder()
        for (i in 0 until chipGroup.childCount) {
            val childView: View = chipGroup.getChildAt(i)
            if (childView is Chip) {
                val chip = childView as Chip
                if (chip.isChecked) {
                    stringBuilder.append(", ").append(chip.text)
                }
            }
        }

        Log.d("TAGClicked", "onCheckedChanged: Clicked")
        textView.text = if (stringBuilder.length > 0) {
            "Selected languages: " + stringBuilder.substring(2)
        } else {
            "No languages selected"
        }
    }
}