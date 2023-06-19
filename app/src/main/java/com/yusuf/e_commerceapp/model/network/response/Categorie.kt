package com.yusuf.e_commerceapp.model.network.response

import android.os.Parcelable
//import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parcelize
@Parcelize
data class Categorie(
    var created_at: String,
    var description: String,
    var id: Int,
    var name: String,
    var picture: String,
    var updated_at: String
): Parcelable