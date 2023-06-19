package com.yusuf.e_commerceapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yusuf.e_commerceapp.PreferencesHandler
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
//            if (PreferencesHandler.getUser(requireActivity()) != null) {
                findNavController().navigate(
                    R.id.action_splashFragment_to_homeFragment
                )
//            } else {
//                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
//            }
        },0L)

    }
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentSplashBinding.inflate(layoutInflater,container,false)

}