package com.vrushank.loginfeaturechallenge.domain.useCases

import com.vrushank.loginfeaturechallenge.data.repository.AuthenticationRepoImp
import com.vrushank.loginfeaturechallenge.domain.repo.AuthenticationRepo
import com.vrushank.loginfeaturechallenge.util.Resources

class ForgotPassword(private val repo: AuthenticationRepo = AuthenticationRepoImp()) {
    suspend operator fun invoke(email: String): Resources<Unit>{
        return repo.forgotPassword(
            email = email
        )
    }
}