package com.example.bankmandirimobileintern.domain.manager.usecase

import com.example.bankmandirimobileintern.domain.manager.LocalUserManager

class SaveAppEntry(
    private val LocalUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        LocalUserManager.saveAppEntry()
    }

}