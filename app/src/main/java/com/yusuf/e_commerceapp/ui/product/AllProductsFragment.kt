package com.yusuf.e_commerceapp.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentAllProductsBinding
import com.yusuf.e_commerceapp.model.network.response.Product
import com.yusuf.e_commerceapp.ui.home.ProductsEpoxyController


class AllProductsFragment : BaseFragment<FragmentAllProductsBinding>() {

    val args: AllProductsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = ProductsEpoxyController(::onProductSelected)
        binding.allProductsRecyclerView.setController(controller)
        controller.products = args.allProducts.toList()
        binding.apply {
            allProductsBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private  fun onProductSelected(product:Product){
        val directions = AllProductsFragmentDirections.actionAllProductsFragmentToProductDetailFragment(
            product = product
        )
        findNavController().navigate(directions)
    }
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAllProductsBinding.inflate(layoutInflater,container,false)

}