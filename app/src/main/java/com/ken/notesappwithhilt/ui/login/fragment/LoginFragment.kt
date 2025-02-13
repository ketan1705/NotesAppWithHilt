package com.ken.notesappwithhilt.ui.login.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cheezycode.notesample.models.UserRequest
import com.ken.notesappwithhilt.ui.login.viewmodel.AuthViewMode
import com.ken.notesappwithhilt.R
import com.ken.notesappwithhilt.databinding.FragmentLoginBinding
import com.ken.notesappwithhilt.utils.NetworkResult
import com.ken.notesappwithhilt.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by activityViewModels<AuthViewMode>()

    @Inject
    lateinit var tokenManager: TokenManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val validationResult = validateUserInput()
            if (validationResult.first) {
                authViewModel.loginUser(getUserRequest())
            } else {
                binding.txtError.text = validationResult.second
            }
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().popBackStack()
        }
        bindObservers()
    }

    private fun bindObservers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    tokenManager.saveToken(it.data!!.token)
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }

                is NetworkResult.Error -> {
                    binding.txtError.text = it.message
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val userRequest = getUserRequest()
        return authViewModel.loginValidateCredentials(
            userRequest.userName,
            userRequest.userPassword,
        )
    }

    private fun getUserRequest(): UserRequest {
        val userName = binding.txtUsername.text.toString()
        val password = binding.txtPassword.text.toString()
        return UserRequest("", password, userName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}