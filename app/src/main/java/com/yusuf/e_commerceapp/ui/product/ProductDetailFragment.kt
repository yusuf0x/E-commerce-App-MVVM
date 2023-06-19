package com.yusuf.e_commerceapp.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.yusuf.e_commerceapp.PreferencesHandler
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentProductDetailBinding
import com.yusuf.e_commerceapp.db.Product
import com.yusuf.e_commerceapp.db.ProductDbViewModel
import com.yusuf.e_commerceapp.hilt.service.AddProductToCartState
import com.yusuf.e_commerceapp.hilt.service.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {
    private val viewModel: ProductsViewModel by viewModels()
    private val productDbViewModel: ProductDbViewModel by viewModels()
    val args: ProductDetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            productDetailBack.setOnClickListener {
                findNavController().navigateUp()
            }
            Picasso.get().load(args.product.picture).into(productDetailImage)
            productDetailPrice.text = args.product.price.toString() + " USD"
            productDetailTitle.text = args.product.name.toString()
            productDetailDescription.text = args.product.description.toString()
//            productDetailRating.text = args.product.
//            productDetailReviews.text = args.product

            productDetailLike.setOnClickListener {
                if (PreferencesHandler.getUser(requireActivity()) != null) {
                    addToWishList()
//            it.setBackgroundResource()
                    it.isVisible = false
                    binding.productDetailLiked.isVisible = true
                    binding.productDetailLiked.setBackgroundResource(R.drawable.ic_wishlist1)
                } else {
                    findNavController().navigate(R.id.action_productDetailFragment_to_loginFragment)
                }
            }

        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addProductToCartState.collect { state ->
                when (state) {
                    is AddProductToCartState.Success -> {
                        Toast.makeText(
                            requireContext(),
                            "added to  cart " + state.response.cart.id.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is AddProductToCartState.Failure -> {
                        Toast.makeText(
                            activity,
                            "error " + state.error.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Error ", state.error.toString());
                    }
                    else -> {}
                }
            }
        }
        binding.productDetailAddToCart.setOnClickListener {
            if (PreferencesHandler.getUser(requireActivity()) != null) {
                viewModel.addProductToCart(
                    PreferencesHandler.getUser(requireContext())!!.id,
                    args.product.id
                )
            } else {
                findNavController().navigate(R.id.action_productDetailFragment_to_loginFragment)
            }
        }
    }


    private fun addToWishList() {

        productDbViewModel.insert(
            Product(
                id = args.product.id,
                name = args.product.name,
                user_id = PreferencesHandler.getUser(requireContext())!!.id,
                quantity = args.product.quantity,
                picture = args.product.picture,
                created_at = args.product.created_at,
                updated_at = args.product.updated_at,
                categorie_id = args.product.categorie_id,
                description = args.product.description,
                price = args.product.price
            )
        )
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentProductDetailBinding.inflate(layoutInflater, container, false)


}