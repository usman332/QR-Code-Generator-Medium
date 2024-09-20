package com.usman.qrcodegenratorscanner.presenter.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val state: MutableState<String> = mutableStateOf(String())

    fun setTextForQR(text: String) {
        state.value = text
    }

    fun getTextForQR(): String {
        return state.value
    }

}