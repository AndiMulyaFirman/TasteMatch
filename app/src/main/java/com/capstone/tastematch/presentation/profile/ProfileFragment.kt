package com.capstone.tastematch.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.capstone.tastematch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private lateinit var profileImageView: ImageView
    private lateinit var emailTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var heightTextView: TextView
    private lateinit var weightTextView: TextView

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        currentUser = firebaseAuth.currentUser!!
        firestore = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileImageView = view.findViewById(R.id.profileImageView)
        emailTextView = view.findViewById(R.id.emailTextView)
        nameTextView = view.findViewById(R.id.nameTextView)
        ageTextView = view.findViewById(R.id.ageTextView)
        heightTextView = view.findViewById(R.id.heightTextView)
        weightTextView = view.findViewById(R.id.weightTextView)

        loadProfileData()

        return view
    }

    private fun loadProfileData() {
        // Mengambil email pengguna dari Firebase Authentication
        val email = currentUser.email
        // Tampilkan email pada TextView yang sesuai
        emailTextView.text = email

        // Mengambil data biodata dari Firestore
        val userId = currentUser.uid
        firestore.collection("user").document(userId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Ambil data nama, umur, tinggi badan, dan berat badan dari dokumen
                    val name = documentSnapshot.getString("name")
                    val age = documentSnapshot.getString("Age")
                    val height = documentSnapshot.getString("Height")
                    val weight = documentSnapshot.getString("Weight")

                    // Tampilkan data pada TextView yang sesuai
                    nameTextView.text = "Name: $name"
                    ageTextView.text = "Age: $age"
                    heightTextView.text = "Height: $height"
                    weightTextView.text = "Weight: $weight"
                }
            }
            .addOnFailureListener { e ->
                // Handle jika terjadi kesalahan saat mengambil data dari Firestore
            }
    }
}
