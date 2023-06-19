package com.yusuf.e_commerceapp.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yusuf.e_commerceapp.databinding.SupportLayoutBinding
import com.yusuf.e_commerceapp.hilt.service.ProductsViewModel
import com.yusuf.e_commerceapp.hilt.service.SendSupportState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupportSheet : BottomSheetDialogFragment() {
    private lateinit var binding: SupportLayoutBinding
    private val viewModel: ProductsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Send.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SupportLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun saveAction() {
        viewModel.sendSupport(
                binding.etName.text.toString(),binding.etEmail.text.toString(),binding.etMessage.text.toString()
        )
        binding.etName.setText("")
        binding.etEmail.setText("")
        binding.etMessage.setText("")

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sendSupportState.collect{ state ->
                when(state){
                    is SendSupportState.Success -> {
                        Toast.makeText(requireContext(), state.response.message.toString(), Toast.LENGTH_LONG).show()
                        Log.d("GOOD",state.response.message.toString());
                    }
                    is SendSupportState.Failure -> {
                        Toast.makeText(requireContext(), state.error.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("Error",state.error.toString());
                    }
                    else -> {
                        Log.d("Nothing","Nothing");
                    }
                }
            }
        }
    }
}
