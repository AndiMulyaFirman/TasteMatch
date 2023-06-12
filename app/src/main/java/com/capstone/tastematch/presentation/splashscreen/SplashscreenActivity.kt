package com.capstone.tastematch.presentation.splashscreen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import com.capstone.tastematch.databinding.ActivitySplashscreenBinding
import com.capstone.tastematch.presentation.auth.login.LoginActivity
import com.capstone.tastematch.presentation.main.MainActivity
import com.google.firebase.auth.FirebaseAuth


@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {

//    private lateinit var userModel: User
//    private lateinit var userPreferences: UserPreferences
    private lateinit var binding: ActivitySplashscreenBinding
    private val delayDuration: Long = 3000
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
//        userPreferences = UserPreferences(this)
//        userModel = userPreferences.getUser()


        Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
        }, delayDuration)

        playAnimation()
    }

    private fun playAnimation() {
        val title1 = ObjectAnimator.ofFloat(binding.tvNameApp, View.ALPHA, 1f).setDuration(1000)
        val title2 = ObjectAnimator.ofFloat(binding.tvDescApp, View.ALPHA, 1f).setDuration(1500)

        AnimatorSet().apply {
            playSequentially(title1, title2)
            start()
        }
    }

//    override fun onStart() {
//        super.onStart()
//
//        if(firebaseAuth.currentUser != null){
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        } else {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }
//    }
}