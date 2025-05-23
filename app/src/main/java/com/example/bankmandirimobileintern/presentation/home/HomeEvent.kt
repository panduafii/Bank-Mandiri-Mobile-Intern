package com.example.bankmandirimobileintern.presentation.home

sealed class HomeEvent {

    data class UpdateScrollValue(val newValue: Int): HomeEvent()
    data class UpdateMaxScrollingValue(val newValue: Int): HomeEvent()

}