package com.example.assignmentapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentapp.data.repository.AuthRepository
import com.example.assignmentapp.data.repository.BaseRepository
import com.example.assignmentapp.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }
}