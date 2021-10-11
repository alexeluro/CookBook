package com.inspiredcoda.cookbook.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspiredcoda.cookbook.data.Repository
import com.inspiredcoda.cookbook.data.response.FruitData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var repository: Repository
) : ViewModel() {

    private var _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private var _fruitDatas = MutableLiveData<List<FruitData>>()
    val fruitDatas: LiveData<List<FruitData>>
        get() = _fruitDatas

    init {
        getFruits()
    }

    private fun getFruits() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingState.postValue(true)
            try {
                val datas = repository.getFoodData()
                _fruitDatas.postValue(datas)
                _loadingState.postValue(false)
            } catch (ex: IOException) {
                _loadingState.postValue(false)
            }
        }
    }


}