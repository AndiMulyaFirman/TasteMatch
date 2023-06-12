package com.capstone.tastematch.presentation.formInput

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.capstone.tastematch.R
import com.capstone.tastematch.databinding.ActivityDataUserBinding
import com.capstone.tastematch.databinding.ActivityDetailBinding
import com.capstone.tastematch.databinding.ActivityLoginBinding
import com.capstone.tastematch.presentation.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.checkerframework.common.returnsreceiver.qual.This

class DataUserActivity : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtWeight: EditText
    private lateinit var btnSave: Button

    private lateinit var progressBar: ProgressBar
    private lateinit var binding: ActivityDataUserBinding

    private var db = FirebaseFirestore.getInstance() // Initialize Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        edtName = findViewById(R.id.edt_name)
        edtAge = findViewById(R.id.edt_age)
        edtHeight = findViewById(R.id.edt_height)
        edtWeight = findViewById(R.id.edt_weight)
        btnSave = findViewById(R.id.btn_save)
//        progressBar = findViewById(R.id.progress_bar)

        btnSave.setOnClickListener {
            val sName = edtName.text.toString().trim()
            val sAge = edtAge.text.toString().trim()
            val sHeight = edtHeight.text.toString().trim()
            val sWeight = edtWeight.text.toString().trim()

            // Add a validation check for empty input fields
            if (sName.isNotEmpty() && sAge.isNotEmpty() && sHeight.isNotEmpty() && sWeight.isNotEmpty()) {
                val userMap = hashMapOf(
                    "name" to sName,
                    "Age" to sAge,
                    "Height" to sHeight,
                    "Weight" to sWeight
                )

                val userId = FirebaseAuth.getInstance().currentUser!!.uid

                db.collection("user").document(userId).set(userMap)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Successfully Added!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }
        }
        playAnimation()
    }

    private fun playAnimation() {
        val dataUserTitle = ObjectAnimator.ofFloat(binding.tvDataUser, View.ALPHA, 1f).setDuration(DURATION2.toLong())
        val nameEdt = ObjectAnimator.ofFloat(binding.edtName, View.ALPHA, 1f).setDuration(DURATION2.toLong())
        val ageEdt = ObjectAnimator.ofFloat(binding.edtAge, View.ALPHA, 1f).setDuration(DURATION2.toLong())
        val heightEdt = ObjectAnimator.ofFloat(binding.edtHeight, View.ALPHA, 1f).setDuration(DURATION2.toLong())
        val weightEdt = ObjectAnimator.ofFloat(binding.edtWeight, View.ALPHA, 1f).setDuration(DURATION2.toLong())
        val saveBtn = ObjectAnimator.ofFloat(binding.btnSave, View.ALPHA, 1f).setDuration(DURATION2.toLong())

        AnimatorSet().apply {
            playSequentially(dataUserTitle, nameEdt, ageEdt, heightEdt, weightEdt, saveBtn)
            startDelay = DURATION2.toLong()
            start()
        }
    }

    companion object {
        const val DURATION2 = 400
    }

    override fun onStart() {
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val userDocumentRef = FirebaseFirestore.getInstance().collection("user").document(userId)

            userDocumentRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        // User data exists, already filled in
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // User data doesn't exist, navigate to the data entry screen
                        // Add your code here to handle the case when user data is not filled in yet
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Failed to check user data!", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error checking user data: ${exception.message}")
                }
        }
    }


}