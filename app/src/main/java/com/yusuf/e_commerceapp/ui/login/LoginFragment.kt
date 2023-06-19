package com.yusuf.e_commerceapp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yusuf.e_commerceapp.hilt.service.auth.LoginState
import com.yusuf.e_commerceapp.PreferencesHandler
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentLoginBinding
import com.yusuf.e_commerceapp.hilt.service.auth.AuthViewModel
import com.yusuf.e_commerceapp.model.mapper.UserMapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect{ state ->
                when(state){
                    is LoginState.Success -> {
                        Toast.makeText(activity, state.response.id.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("Error ",state.response.id.toString());
                        PreferencesHandler.setUser(requireActivity(), UserMapper.buildFrom(state.response))
                        findNavController().navigate(
                            R.id.action_loginFragment_to_homeFragment
                        )
                    }
                    is LoginState.Failure -> {
                        Toast.makeText(activity, state.error.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("Error ",state.error.toString());
                    }
                    else -> {}
                }
            }
        }
        binding.apply {
            loginSignup.setOnClickListener {
                findNavController().navigate(
                    R.id.action_loginFragment_to_registerFragment
                )
            }
            loginButton.setOnClickListener {
                loginUser()
//                findNavController().navigate(
//                    R.id.action_loginFragment_to_homeFragment
//                )
            }
            forgotPassword.setOnClickListener {
                findNavController().navigate(
                    R.id.action_loginFragment_to_forgotPasswordFragment
                )
            }
        }
    }
    private fun loginUser() {
        viewModel.login(
            email = binding.loginInputEmail.text.toString(),
            password = binding.loginInputPassword.text.toString(),
        )
    }
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentLoginBinding.inflate(layoutInflater,container,false)

}