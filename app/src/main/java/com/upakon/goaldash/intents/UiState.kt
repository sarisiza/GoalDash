package com.upakon.goaldash.intents

sealed class UiState<out T>{

    object LOADING : UiState<Nothing>()

    data class SUCCESS<T>(val information: T) : UiState<T>()

    data class ERROR(var error: Exception): UiState<Nothing>()

}
