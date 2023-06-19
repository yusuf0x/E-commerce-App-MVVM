package com.yusuf.e_commerceapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.yusuf.e_commerceapp.databinding.FragmentHomeBinding
import com.yusuf.e_commerceapp.extensions.capitalize
import com.yusuf.e_commerceapp.hilt.service.GetAllCategoriesState
import com.yusuf.e_commerceapp.hilt.service.GetAllProductsState
import com.yusuf.e_commerceapp.hilt.service.ProductsViewModel
import com.yusuf.e_commerceapp.model.domain.User
import com.yusuf.e_commerceapp.model.network.response.Categorie
import com.yusuf.e_commerceapp.model.network.response.GetAllCategoryResponse
import com.yusuf.e_commerceapp.model.network.response.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.PI

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var allCategories: List<Categorie>
    private lateinit var allProducts: List<Product>

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user:User? = PreferencesHandler.getUser(requireActivity())
//        Toast.makeText(requireActivity(), user?.email.toString(), Toast.LENGTH_SHORT).show()


        val controller1 = CategoriesEpoxyController()
        val controller2 = ProductsEpoxyController(::onProductSelected)
        binding.homeCategoriesRecyclerView.setController(controller1)
        binding.homeRecyclerView.setController(controller2)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.allCategoriesState.collect{ state->
                when(state){
                    is GetAllCategoriesState.Success -> {
                        controller1.items = state.response.categorie
                        allCategories = state.response.categorie
                    }
                    is GetAllCategoriesState.Failure -> {
                        Toast.makeText(activity, state.error.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("Error ",state.error.toString());
                    }
                    else -> {}
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.allProductsState.collect{ state->
                when(state){
                    is GetAllProductsState.Success -> {
                        controller2.products = state.response.Product
                        allProducts = state.response.Product
                    }
                    is GetAllProductsState.Failure -> {
                        Toast.makeText(activity, state.error.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("Error ",state.error.toString());
                    }
                    else -> {}
                }
            }
        }
        viewModel.getAllCategories()
        viewModel.getAllProducts()

        binding.apply {
            if(user==null) {
                textView6.text = "Hello sir"
                Picasso.get().load("https://robohash.org/hello").into(homeProfile)
            }else{
                textView6.text =
                    "Hi,${user.firstname.capitalize()} ${user.lastname?.capitalize()}"
                Picasso.get().load(user.image.toString()).into(homeProfile)
            }
            homeProfile.setOnClickListener {
                if (PreferencesHandler.getUser(requireActivity()) != null) {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_profileFragment
                    )
                }else{
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }

            }
            homeCart.setOnClickListener {
                if (PreferencesHandler.getUser(requireActivity()) != null) {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_cartFragment
                    )
                }else{
                    findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                }
            }
            search.setOnClickListener {
//                findNavController().navigate(
//                    R.id.action_homeFragment_to_searchFragment
//                )
                val directions = HomeFragmentDirections.actionHomeFragmentToSearchFragment(
                    allProducts = allProducts.toTypedArray(),
                    query=binding.search.text.toString()
                )
                findNavController().navigate(
                    directions
                )
            }
            seeAll.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToCategoryFragment(
                    allCategories = allCategories.toTypedArray()
                )
                findNavController().navigate(
                    directions
                )
            }
            seeAll1.setOnClickListener {
                val directions = HomeFragmentDirections.actionHomeFragmentToAllProductsFragment(
                    allProducts = allProducts.toTypedArray()
                )
                findNavController().navigate(
                    directions
                )
            }
        }



    }
    private  fun onProductSelected(product:Product){
        val directions = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
            product = product
        )
        findNavController().navigate(directions)
    }
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentHomeBinding.inflate(layoutInflater,container,false)

}