package com.example.lifecycledemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Counter : ViewModel() {
    private val data = MutableLiveData(0)
    val counter: LiveData<Int> get() = data

    fun increment() {
        data.value = (data.value ?: 0) + 1
    }

    fun decrement() {
        data.value = (data.value ?: 0) - 1
    }
}