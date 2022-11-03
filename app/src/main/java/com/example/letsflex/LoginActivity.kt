package com.example.letsflex

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.letsflex.databinding.ActivityLoginBinding
import com.example.letsflex.databinding.ActivityRegisterBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var database: DatabaseReference
private lateinit var binding: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener() {
            val email = binding.editTxtMail.text.toString()
            val password = binding.editTxtPassword.text.toString()
            if (email.isNotEmpty()) {
                if (password.isNotEmpty()) {
                    authenticateUser(email, password)
                } else {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtRegister.setOnClickListener() {
            //The login and register activities finish when leaving so that the user can't go back after logging in
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
            startActivity(intent)
        }
    }

    private fun authenticateUser(email: String, userPassword: String) {
        val sharedPreference: SharedPreferences = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()

        database = FirebaseDatabase.getInstance().getReference("Users")
        database.get().addOnSuccessListener { users ->
            users.children.forEach() {
                if (it.child("email").value.toString() == email) {
                    if (it.child("password").value.toString() == userPassword) {
                        //Store the username as login data in the shared preferences
                        editor.putString("username", it.child("username").value.toString())
                        editor.putString("avatar", it.child("avatar").value.toString())
                        editor.putString("password", it.child("password").value.toString())
                        editor.apply()

                        //LOG IN
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        finish()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to reach the database", Toast.LENGTH_SHORT).show()
        }


        /*
        database.child(username).get().addOnSuccessListener {
            //The datasnapshot is called 'it'!
            if (it.exists()) {
                //User exists
                if (it.child("password").value.toString() == userPassword) {
                    //Store the username as login data in the shared preferences
                    editor.putString("username", username)
                    editor.putString("avatar", it.child("avatar").value.toString())
                    editor.putString("password", it.child("password").value.toString())
                    editor.apply()


                    //LOG IN
                    /*
                    val intent = Intent(this@MainActivity, UserActivity::class.java)
                    startActivity(intent)
                     */
                }
                else {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Cannot find the user", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to reach the database", Toast.LENGTH_SHORT).show()
        }

         */
    }
}