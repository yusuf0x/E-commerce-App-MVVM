package com.yusuf.e_commerceapp.epoxy

import android.annotation.SuppressLint
import android.util.Log
import com.squareup.picasso.Picasso
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.databinding.CartItemBinding
import com.yusuf.e_commerceapp.databinding.CategoryItemBinding
import com.yusuf.e_commerceapp.databinding.LayoutQuantityBinding
import com.yusuf.e_commerceapp.model.network.response.Message
import java.math.BigDecimal
import java.text.NumberFormat

class CartItemEpoxyModel(
    val message: Message,
    private val onQuantityChanged: (Int) -> Unit
) : ViewBindingKotlinModel<CartItemBinding>(R.layout.cart_item) {
    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    @SuppressLint("SetTextI18n")
    override fun CartItemBinding.bind() {
        Picasso.get().load(message.picture).into(productImage)
        productTitleTextView.text = message.name
        quantityView.quantityTextView.text = message.quantity.toString()

        cartItemDelete.setOnClickListener {
            Log.d("DELETE_CART", "DELETE_CART")
        }
        quantityView.apply {
            quantityTextView.text = message.quantity.toString()
            minusImageView.setOnClickListener {
                quantityView.quantityTextView.text = message.quantity.toString()
//                onQuantityChanged(message.quantity - 1)
                updatePrice(this@bind,message.quantity-1)
            }
            plusImageView.setOnClickListener {
                quantityView.quantityTextView.text = message.quantity.toString()
//                onQuantityChanged(message.quantity + 1)
                updatePrice(this@bind,message.quantity+1)
            }
        }
        val totalPrice = BigDecimal(message.price) * BigDecimal(message.quantity)
        totalProductPriceTextView.text = currencyFormatter.format(totalPrice)

    }
    fun updatePrice(bind: CartItemBinding, i: Int){
        val totalPrice = BigDecimal(message.price) * BigDecimal(i)
        bind.totalProductPriceTextView.text = currencyFormatter.format(totalPrice)
        bind.quantityView.quantityTextView.text = i.toString()
    }




}