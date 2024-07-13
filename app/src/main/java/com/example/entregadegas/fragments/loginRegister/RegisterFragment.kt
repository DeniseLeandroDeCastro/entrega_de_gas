package com.example.entregadegas.fragments.loginRegister

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.entregadegas.data.User
import com.example.entregadegas.databinding.FragmentRegisterBinding
import com.example.entregadegas.util.RegisterValidation
import com.example.entregadegas.util.Resource
import com.example.entregadegas.viewmodel.RegisterViewModel
import com.google.android.material.internal.ViewUtils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment: Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            editFirstNameRegister.setOnFocusChangeListener { _, focused ->
                if (!focused) hideKeyboard(editFirstNameRegister)
            }
            editLastNameRegister.setOnFocusChangeListener { _, focused ->
                if (!focused) hideKeyboard(editLastNameRegister)
            }
            editEmailRegister.setOnFocusChangeListener { _, focused ->
                if (!focused) hideKeyboard(editEmailRegister)
            }
            editPasswordRegister.setOnFocusChangeListener { _, focused ->
                if (!focused) hideKeyboard(editPasswordRegister)
            }
        }

        binding.apply {
            buttonRegisterRegister.setOnClickListener {
                val user = User (
                    editFirstNameRegister.text.toString().trim(),
                    editLastNameRegister.text.toString().trim(),
                    editEmailRegister.text.toString().trim(),
                )
                val password = editPasswordRegister.text.toString().trim()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }
        }
        lifecycleScope.launch {
            viewModel.register.collect {
                when(it) {
                    is Resource.Loading -> {
                        Log.d("RegisterFragment", it.message.toString())
                    }
                    is Resource.Success -> {
                        Log.d("RegisterFragment", it.data.toString())
                    }
                    is Resource.Error -> {
                        Log.e("RegisterFragment", it.message.toString())
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launch{
            viewModel.validation.collect { validation ->
                if (validation.email is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.editEmailRegister.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }

                if (validation.password is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.editPasswordRegister.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }
        }
    }
}