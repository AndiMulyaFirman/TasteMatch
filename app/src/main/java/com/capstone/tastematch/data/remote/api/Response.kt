package com.capstone.tastematch.data.remote.api

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity

@Parcelize
data class Response(
	val response: List<ResponseItem?>? = null
) : Parcelable

@Parcelize
@Entity(tableName = "menudb")
data class ResponseItem(
	val namaMakanan: String? = null,
	val langkahPembuatan: List<String?>? = null,
	val jumlahKaloriPerPorsiKkal: Int? = null,
	val imageURL: String? = null,
	val id: String? = null,
	val bahanBahan: List<String?>? = null
) : Parcelable
