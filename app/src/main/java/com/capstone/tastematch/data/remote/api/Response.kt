package com.capstone.tastematch.data.remote.api

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
data class Response(
	val response: List<ResponseItem>
) : Parcelable

@Parcelize
@Entity(tableName = "menudb")
data class ResponseItem(
	val namaMakanan: String,
	val langkahPembuatan: List<String>,
	val jumlahKaloriPerPorsiKkal: Int,
	val imageURL: String,
	@PrimaryKey
	val id: String,
	val bahanBahan: List<String>
) : Parcelable
