package com.vrushank.loginfeaturechallenge.domain.useCases

import com.vrushank.loginfeaturechallenge.data.repository.AuthenticationRepoImp
import com.vrushank.loginfeaturechallenge.domain.model.User
import com.vrushank.loginfeaturechallenge.domain.repo.AuthenticationRepo
import com.vrushank.loginfeaturechallenge.util.Resources

class LoginUseCase(private val repository: AuthenticationRepo = AuthenticationRepoImp()) {
    suspend operator fun invoke(email: String,password: String): Resources<User> {
        return repository.login(email,password)
    }


}