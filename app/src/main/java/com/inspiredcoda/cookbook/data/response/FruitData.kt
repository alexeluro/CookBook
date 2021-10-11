package com.inspiredcoda.cookbook.data.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FruitData(
    @SerializedName("description")
    var description: String?,
    @SerializedName("from")
    var from: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("nutrients")
    var nutrients: String?,
    @SerializedName("organic")
    var organic: Boolean?,
    @SerializedName("price")
    var price: String?,
    @SerializedName("productName")
    var productName: String?,
    @SerializedName("quantity")
    var quantity: String?
): Parcelable