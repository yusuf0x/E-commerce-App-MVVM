package com.yusuf.e_commerceapp.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.yusuf.e_commerceapp.PreferencesHandler
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentProfileBinding
import com.yusuf.e_commerceapp.extensions.capitalize
import com.yusuf.e_commerceapp.hilt.service.ProductsViewModel
import com.yusuf.e_commerceapp.hilt.service.SendSupportState
import com.yusuf.e_commerceapp.model.domain.User
import com.yusuf.e_commerceapp.model.mapper.UserMapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewModel: ProductsViewModel by viewModels()
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val user: User? = PreferencesHandler.getUser(requireActivity())

        binding.apply {
            if(user==null) {
                profileName.text = "user"
                profileEmail.text = "user@gmail.com"
                Picasso.get().load("https://robohash.org/hello").into(profileImage)
            }else{
                profileName.text =
                    "${user.firstname.capitalize()} ${user.lastname.capitalize()}"
                profileEmail.text = user.email
                Picasso.get().load(user.image.toString()).into(profileImage)
            }
            editProfile.setOnClickListener {
                findNavController().navigate(
                    R.id.action_profileFragment_to_profileEditFragment
                )
            }
            profileWishlist.setOnClickListener {
                findNavController().navigate(
                    R.id.action_profileFragment_to_wishListFragment
                )
            }
            profileBack.setOnClickListener {
                findNavController().navigateUp()
            }


            profileSupport.setOnClickListener {

                SupportSheet().show(requireActivity().supportFragmentManager,"support")


            }
            profileLogout.setOnClickListener {
                PreferencesHandler.setUser(requireActivity(),null)
                findNavController().navigate(
                    R.id.action_profileFragment_to_splashFragment
                )
            }
        }

    }
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentProfileBinding.inflate(layoutInflater,container,false)

}