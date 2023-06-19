package com.yusuf.e_commerceapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
//import com.google.android.material.search.SearchView
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.navArgs

import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentSearchBinding
import com.yusuf.e_commerceapp.model.network.response.Product
import com.yusuf.e_commerceapp.ui.home.ProductsEpoxyController
import com.yusuf.e_commerceapp.ui.product.AllProductsFragmentArgs
import com.yusuf.e_commerceapp.ui.product.AllProductsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    val args: SearchFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = ProductsEpoxyController(::onProductSelected)
        binding.searchRecyclerView.setController(controller)
        val query1 = args.query;

        controller.products = args.allProducts.filter { it.name.contains("$query1",ignoreCase = true) }.toList()
//        binding.searchView.requestFocus()
//        binding.searchView.setQuery(query1, true);
        // Set the submit button enabled (optional)
//        binding.searchView.setSubmitButtonEnabled(true);
        binding.searchBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                controller.products = args.allProducts.filter {
                    it.name.contains("$query",ignoreCase = true)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }
    private  fun onProductSelected(product: Product){
//        val directions = AllProductsFragmentDirections.actionAllProductsFragmentToProductDetailFragment(
//            product = product
//        )
//        findNavController().navigate(directions)
    }
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentSearchBinding.inflate(layoutInflater,container,false)

}