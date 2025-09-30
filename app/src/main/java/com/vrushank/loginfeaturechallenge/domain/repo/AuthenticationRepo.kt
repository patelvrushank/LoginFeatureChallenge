package com.vrushank.loginfeaturechallenge.domain.repo

import com.vrushank.loginfeaturechallenge.domain.model.User
import com.vrushank.loginfeaturechallenge.util.Resources

interface AuthenticationRepo {
    suspend fun login(email: String, password: String): Resources<User>
    suspend fun logout(): Resources<Unit>
    suspend fun forgotPassword(email: String): Resources<Unit>
}