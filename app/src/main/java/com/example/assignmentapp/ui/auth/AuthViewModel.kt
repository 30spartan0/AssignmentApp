package com.example.assignmentapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentapp.data.network.Resource
import com.example.assignmentapp.data.repository.AuthRepository
import com.example.assignmentapp.data.responses.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        username: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(username, password)
    }

    fun saveAuthToken(token: String) = viewModelScope.launch{
        repository.saveAuthToken(token)
    }
}