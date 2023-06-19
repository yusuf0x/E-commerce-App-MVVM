package com.yusuf.e_commerceapp.ui.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.yusuf.e_commerceapp.PreferencesHandler
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentHomeBinding
import com.yusuf.e_commerceapp.databinding.FragmentWishListBinding
import com.yusuf.e_commerceapp.db.Product
import com.yusuf.e_commerceapp.db.ProductDbViewModel
import com.yusuf.e_commerceapp.ui.product.AllProductsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishListFragment : BaseFragment<FragmentWishListBinding>() {

    private val productDbViewModel:ProductDbViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = WishListEpoxyController(::onProductSelected,::onProductDelete)
        binding.wishlistRecyclerView.setController(controller)
        lifecycle.coroutineScope.launch {
            productDbViewModel.allProductsById(PreferencesHandler.getUser(requireContext())!!.id).collect() {
                controller.products = it
            }
        }
        binding.wishlistBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private  fun onProductSelected(product: Product){
        val directions = AllProductsFragmentDirections.actionAllProductsFragmentToProductDetailFragment(
            product = com.yusuf.e_commerceapp.model.network.response.Product(
                id = product.id,
                name = product.name,
                quantity = product.quantity,
                picture = product.picture,
                created_at = product.created_at,
                updated_at = product.updated_at,
                categorie_id = product.categorie_id,
                description = product.description,
                price = product.price
            )
        )
        findNavController().navigate(directions)
    }
    private fun onProductDelete(product: Product){
        productDbViewModel.delete(product)
    }
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentWishListBinding.inflate(layoutInflater,container,false)

}