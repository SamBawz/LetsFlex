package com.example.letsflex

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.letsflex.databinding.ActivityRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

@SuppressLint("StaticFieldLeak")
private lateinit var binding : ActivityRegisterBinding
private lateinit var database : DatabaseReference

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtLogin.setOnClickListener() {
            //The login and register activities finish when leaving so that the user can't go back after logging in
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener() {
            val email = binding.editTxtRegisterEmail.text.toString()
            val name = binding.editTxtRegisterName.text.toString()
            val password1 = binding.editTxtRegisterPassword.text.toString()
            val password2 = binding.editTxtRegisterPassword2.text.toString()

            if (email.isNotEmpty() && name.isNotEmpty() && password1.isNotEmpty() && password2.isNotEmpty()) {
                if (password1 == password2) {
                    database = FirebaseDatabase.getInstance().getReference("Users")
                    val user = User(email, name, password1)
                    val entryId = database.push().key.toString()
                    database.child(entryId).setValue(user).addOnSuccessListener {
                        binding.editTxtRegisterEmail.text.clear()
                        binding.editTxtRegisterName.text.clear()
                        binding.editTxtRegisterPassword.text.clear()
                        binding.editTxtRegisterPassword2.text.clear()
                        Toast.makeText(this, "Succesfully added", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}