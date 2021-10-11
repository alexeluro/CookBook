package com.inspiredcoda.cookbook.data

import com.inspiredcoda.cookbook.data.response.FruitData

interface Repository {

    suspend fun getFoodData(): List<FruitData>

}