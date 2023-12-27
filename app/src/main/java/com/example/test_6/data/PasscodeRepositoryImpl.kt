package com.example.test_6.data

import com.example.test_6.domain.Passcode
import com.example.test_6.domain.PasscodeRepository
import javax.inject.Inject

class PasscodeRepositoryImpl @Inject constructor() : PasscodeRepository {
    override suspend fun verifyPasscode(inputPasscode: String): Boolean {
        val storedPasscode = getStoredPasscode().passcode
        return inputPasscode == storedPasscode
    }

    override fun getStoredPasscode(): Passcode {
        return Passcode("0934")
    }
}
