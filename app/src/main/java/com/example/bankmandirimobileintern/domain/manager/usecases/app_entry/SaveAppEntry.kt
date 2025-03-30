package com.example.bankmandirimobileintern.domain.manager.usecases.app_entry

import com.example.bankmandirimobileintern.domain.manager.LocalUserManager

class SaveAppEntry(
    private val LocalUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        LocalUserManager.saveAppEntry()
    }

}