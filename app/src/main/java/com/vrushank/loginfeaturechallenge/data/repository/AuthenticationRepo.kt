package com.vrushank.loginfeaturechallenge.data.repository

import com.vrushank.loginfeaturechallenge.domain.model.User
import com.vrushank.loginfeaturechallenge.domain.repo.AuthenticationRepo
import com.vrushank.loginfeaturechallenge.util.Resources

class AuthenticationRepoImp(): AuthenticationRepo {
    val validEmail = "patelvd99@gmail.com"
    val validPassword = "123456"
    override suspend fun login(
        email: String,
        password: String
    ): Resources<User> {
        return if (email == validEmail && password == validPassword){
            Resources.Success(User("",email))
        }else{
            Resources.Error("Invalid email or password")
        }

    }

    override suspend fun logout(): Resources<Unit> {
        return Resources.Success(Unit)
    }

    override suspend fun forgotPassword(email: String): Resources<Unit> {
        return if (email==validEmail){
            Resources.Success(Unit)
        }
        else{
            Resources.Error("Email is not registered")
        }
    }


}