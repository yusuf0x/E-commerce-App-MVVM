package com.yusuf.e_commerceapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentCategoryBinding
import com.yusuf.e_commerceapp.ui.home.CategoriesEpoxyController
import com.yusuf.e_commerceapp.ui.home.ProductsEpoxyController

class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {
    val args:CategoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = CategoryFragmentEpoxyController()
        binding.cartRecyclerView.setController(controller)
        controller.items = args.allCategories.toList()
//        arguments.getParcelable()
        binding.apply {
            cartBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =  FragmentCategoryBinding.inflate(layoutInflater,container,false)

}