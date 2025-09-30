package com.vrushank.loginfeaturechallenge.domain.useCases

import com.vrushank.loginfeaturechallenge.data.repository.AuthenticationRepoImp
import com.vrushank.loginfeaturechallenge.domain.repo.AuthenticationRepo
import com.vrushank.loginfeaturechallenge.util.Resources

class Logout(private val repo: AuthenticationRepo = AuthenticationRepoImp()) {
    suspend operator fun invoke(): Resources<Unit>{
        return repo.logout()
    }
}