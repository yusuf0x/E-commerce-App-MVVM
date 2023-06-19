package com.yusuf.e_commerceapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.send.setOnClickListener {
            findNavController().navigate(
                R.id.action_forgotPasswordFragment_to_passwordResetVerificationFragment
            )
        }
    }
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentForgotPasswordBinding.inflate(layoutInflater,container,false)


}