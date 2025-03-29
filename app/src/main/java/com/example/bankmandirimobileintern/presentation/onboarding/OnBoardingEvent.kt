package com.example.bankmandirimobileintern.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.example.bankmandirimobileintern.domain.manager.usecase.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class OnBoardingEvent {

    object SaveAppEntry: OnBoardingEvent()

}