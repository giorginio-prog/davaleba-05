package com.example.ge.msda.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

private val Any?.isSuccessful: Unit
    get() = Unit

class RegisterActivity : AppCompatActivity() {


    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var registerButton: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        mAuth = FirebaseAuth.getInstance()
        emailInput = findViewById(R.id.signUpEmail)
        passwordInput = findViewById(R.id.signUpPassword)
        registerButton = findViewById(R.id.signUpButton)

        registerButton.setOnClickListener {

            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()

                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show()
                    }
                }

        }
        registerButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }


}

private fun <TResult> Task<TResult>.addOnCanceledListener(function: (Any?) -> Unit) {

}
