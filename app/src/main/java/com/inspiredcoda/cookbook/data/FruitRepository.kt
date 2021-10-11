package com.inspiredcoda.cookbook.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inspiredcoda.cookbook.data.response.FruitData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FruitRepository @Inject constructor(
    @ApplicationContext val context: Context,
    val gson: Gson
) : Repository {

    override suspend fun getFoodData(): List<FruitData> {
        val inputStream = context.assets.open("data.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        return gson.fromJson(jsonString, object : TypeToken<List<FruitData>>() {}.type)
    }
}