package com.yusuf.e_commerceapp.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yusuf.e_commerceapp.hilt.service.auth.RegisterState
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentRegisterBinding
import com.yusuf.e_commerceapp.hilt.service.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.registerState.collect{ state ->
                when(state){
                    is RegisterState.Success -> {
                        Toast.makeText(activity, state.response.code.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("Error ",state.response.code.toString());
                        verifyCode(state.response.code)
                    }
                    is RegisterState.Failure -> {
                        Toast.makeText(activity, state.error.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("Error ",state.error.toString());
                    }
                    else -> {}
                }
            }
        }

        binding.apply {
            registerButton.setOnClickListener {
                registerUser()
            }
            registerLogin.setOnClickListener {
                findNavController().navigate(
                    R.id.action_registerFragment_to_loginFragment
                 )
            }
        }
    }
    private fun verifyCode(code : Int){
        val directions = RegisterFragmentDirections.actionRegisterFragmentToVerifyEmailFragment(
            code = code,
            email = binding.registerInputEmail.text.toString(),
            password = binding.registerInputPassword.text.toString(),
            nom = binding.registerInputFirstName.text.toString(),
            prenom = binding.registerInputLastName.text.toString()
        )
        findNavController().navigate(directions)
    }
    private fun registerUser() {
        viewModel.register(
            email = binding.registerInputEmail.text.toString(),
            password = binding.registerInputPassword.text.toString(),
            nom = binding.registerInputFirstName.text.toString(),
            prenom = binding.registerInputLastName.text.toString()
        )
    }
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =  FragmentRegisterBinding.inflate(layoutInflater,container,false)
}