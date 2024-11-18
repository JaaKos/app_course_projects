package com.example.ajastin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class Timer : ViewModel() {
    private val value = MutableStateFlow(0)
    val time: StateFlow<Int> get() = value

    init {
        viewModelScope.launch {
            while (true){
                flow {
                    emit(Unit)
                    delay(1000L)
                }.collect {
                    value.update { it + 1 }
                }
            }
        }
    }
}