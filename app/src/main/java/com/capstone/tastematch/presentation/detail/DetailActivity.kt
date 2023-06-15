package com.capstone.tastematch.presentation.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.tastematch.R
import com.capstone.tastematch.data.remote.api.ResponseItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var textViewNamaMakanan: TextView
    private lateinit var textViewKalori: TextView
    private lateinit var textViewBahan: TextView
    private lateinit var textViewLangkahPembuatan: TextView

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageView = findViewById(R.id.imageView)
        textViewNamaMakanan = findViewById(R.id.textViewNamaMakanan)
        textViewKalori = findViewById(R.id.textViewKalori)
        textViewBahan = findViewById(R.id.textViewBahan)
        textViewLangkahPembuatan = findViewById(R.id.textViewLangkahPembuatan)

        firestore = FirebaseFirestore.getInstance()

        val responseItem = intent.getParcelableExtra<ResponseItem>(EXTRA_RESPONSE_ITEM)
        responseItem?.let { displayData(it) }

        val fabBack: FloatingActionButton = findViewById(R.id.fabBack)
        fabBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun displayData(responseItem: ResponseItem) {
        Picasso.get().load(responseItem.imageURL).into(imageView)
        textViewNamaMakanan.text = responseItem.menu
        textViewKalori.text = "Kalori: ${responseItem.kalori} Kkal"

        // Mengambil data dari Firestore berdasarkan menu
        val menu = responseItem.menu
        firestore.collection("menu")
            .whereEqualTo("menu", menu)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val document = querySnapshot.documents[0]
                    val bahanList = document.get("bahan") as List<String>
                    val langkahPembuatanList = document.get("langkahPembuatan") as List<String>

                    val bahanText = bahanList.joinToString("\n")
                    val langkahPembuatanText = langkahPembuatanList.joinToString("\n")

                    textViewBahan.text = bahanText
                    textViewLangkahPembuatan.text = langkahPembuatanText
                }
            }
    }

    companion object {
        const val EXTRA_RESPONSE_ITEM = "extra_response_item"
    }
}
