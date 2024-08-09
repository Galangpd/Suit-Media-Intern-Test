package com.example.suitmedia.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmedia.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpAction()
    }

    private fun setUpAction(){

        binding.nextButton.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("NAME", name)
            startActivity(intent)
        }

        binding.checkButton.setOnClickListener {
            checkPalindrome()
        }
    }

    private fun checkPalindrome(){
        val inputText = binding.editTextPalindrome.text.toString()

        val cleanedText = inputText.replace("\\s".toRegex(), "").lowercase()

        val isPalindrome = cleanedText == cleanedText.reversed()

        AlertDialog.Builder(this)
            .setTitle("Palindrome Check")
            .setMessage(if (isPalindrome) {
                "is palindrome."
            } else {
                "is not palindrome."
            })
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}