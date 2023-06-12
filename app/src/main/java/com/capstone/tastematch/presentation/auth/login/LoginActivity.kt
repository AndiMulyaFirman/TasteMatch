package com.capstone.tastematch.presentation.auth.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.tastematch.databinding.ActivityLoginBinding
import com.capstone.tastematch.presentation.auth.register.RegisterActivity
import com.capstone.tastematch.presentation.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun playAnimation() {
        val loginTitle = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(DURATION2.toLong())
        val editTextLogin = ObjectAnimator.ofFloat(binding.edtEmail, View.ALPHA, 1f).setDuration(DURATION2.toLong())
        val editTextPassword = ObjectAnimator.ofFloat(binding.edtPassword, View.ALPHA, 1f).setDuration(DURATION2.toLong())
        val btnLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(DURATION2.toLong())

        AnimatorSet().apply {
            playSequentially(loginTitle, editTextLogin, editTextPassword, btnLogin)
            startDelay = DURATION2.toLong()
            start()
        }
    }

    companion object {
        const val DURATION2 = 400
    }
//  masih terjadi error ketika regsiter di lakukan maka akan menuju ke halaman main, di karenakan code di bawah ini,
//    kode ini berguna untuk memastikan tidak masuk ke halaman login jika sudah pernah login sebelumnya
//    override fun onStart() {
//        super.onStart()
//
//        if(firebaseAuth.currentUser != null){
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }
}