package com.yusuf.e_commerceapp.ui.cart

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
import com.yusuf.e_commerceapp.PreferencesHandler
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentCartBinding
import com.yusuf.e_commerceapp.hilt.service.GetAllCartsState
import com.yusuf.e_commerceapp.hilt.service.GetAllCategoriesState
import com.yusuf.e_commerceapp.hilt.service.ProductsViewModel
import com.yusuf.e_commerceapp.model.network.response.Message
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.text.NumberFormat

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    private val viewModel: ProductsViewModel by viewModels()
    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = CartsEpoxyController()
        binding.cartRecyclerView.setController(controller)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.allCartsState.collect{ state->
                when(state){
                    is GetAllCartsState.Success -> {
                        controller.carts = state.response.message
                        updateTotalLayout(controller.carts)
                    }
                    is GetAllCartsState.Failure -> {
                        Toast.makeText(activity, state.error, Toast.LENGTH_SHORT).show()
                        Log.d("Error ",state.error.toString());
                    }
                    else -> {}
                }
            }
        }
            val id = PreferencesHandler.getUser(requireContext())!!.id
            Toast.makeText(requireActivity(), ""+id, Toast.LENGTH_SHORT).show()
            viewModel.getAllCarts(id)
        binding.cartBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun updateTotalLayout(productsInCart: List<Message>) {
        val totalAmount = productsInCart.sumOf { BigDecimal(it.quantity) * BigDecimal(it.price)}
        val description = "${productsInCart.size} items for ${currencyFormatter.format(totalAmount)}"
        binding.totalPrice.text = description
        binding.cartCheckout.isEnabled = productsInCart.isNotEmpty()
    }
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentCartBinding.inflate(layoutInflater,container,false)


}