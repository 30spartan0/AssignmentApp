package com.example.assignmentapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.assignmentapp.databinding.FragmentLoginBinding
import com.example.assignmentapp.data.network.AuthApi
import com.example.assignmentapp.data.network.Resource
import com.example.assignmentapp.data.repository.AuthRepository
import com.example.assignmentapp.ui.base.BaseFragment
import com.example.assignmentapp.ui.enable
import com.example.assignmentapp.ui.home.HomeActivity
import com.example.assignmentapp.ui.startNewActivity
import com.example.assignmentapp.ui.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visible(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            binding.progressBar.visible(false)


            when (it) {
                is Resource.Success -> {
                    // Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                    viewModel.saveAuthToken(it.value.user.token)
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }

                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }

        })




        binding.buttonLogin.setOnClickListener {
            val username = binding.editUsernameInput.text.toString().trim()
            val password = binding.editPasswordInput.text.toString().trim()
            //Do some validations also
            binding.progressBar.visible(true)
            viewModel.login(username, password)
        }

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}