package com.yusuf.e_commerceapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentPasswordResetVerificationBinding

class PasswordResetVerificationFragment : BaseFragment<FragmentPasswordResetVerificationBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPasswordResetVerificationBinding.inflate(layoutInflater,container,false)

}