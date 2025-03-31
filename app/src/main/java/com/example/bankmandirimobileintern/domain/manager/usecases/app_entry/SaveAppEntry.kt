package com.example.bankmandirimobileintern.domain.manager.usecases.app_entry

import com.example.bankmandirimobileintern.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val LocalUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        LocalUserManager.saveAppEntry()
    }

}