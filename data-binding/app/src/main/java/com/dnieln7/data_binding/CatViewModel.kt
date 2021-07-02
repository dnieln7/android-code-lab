    package com.dnieln7.data_binding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CatViewModel : ViewModel() {
    private val _name = MutableLiveData("Akira")
    private val _age = MutableLiveData(2)
    private val _likes = MutableLiveData(0)
    private val _noLikes = MutableLiveData(0)

    val name: LiveData<String> = _name
    val age: LiveData<Int> = _age
    val likes: LiveData<Int> = _likes
    val noLikes: LiveData<Int> = _noLikes

    fun onLike() {
        _likes.value = (_likes.value ?: 0) + 1
    }

    fun onNotLike() {
        _noLikes.value = (_noLikes.value ?: 0) + 1
    }
}