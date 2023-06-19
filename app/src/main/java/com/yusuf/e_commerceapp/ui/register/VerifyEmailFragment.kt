package com.yusuf.e_commerceapp.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yusuf.e_commerceapp.hilt.service.auth.COnfirmationState
import com.yusuf.e_commerceapp.R
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentVerifyEmailBinding
import com.yusuf.e_commerceapp.hilt.service.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyEmailFragment : BaseFragment<FragmentVerifyEmailBinding>() {

    private val safeArgs : VerifyEmailFragmentArgs by navArgs()
    private var code:String? = null;

    private val viewModel: AuthViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.codePin.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                Log.d("Test Pin", "onTextChanged() called with: s = [" + p0 + "], start = [" + p1 + "], before = [" + p2 + "], count = [" + p3 + "]");
                code = p0.toString()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.verifyEmail.setOnClickListener {
            if(safeArgs.code.toString() == code){
                Toast.makeText(activity, "verified", Toast.LENGTH_SHORT).show()
                confirmRegistration()

            }else{
                Toast.makeText(activity, "please enter correct code", Toast.LENGTH_SHORT).show()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.confirmationState.collect{ state ->
                when(state){
                    is COnfirmationState.Success -> {
                        Toast.makeText(activity, state.response.id.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("Error ",state.response.id.toString());
                        findNavController().navigate(
                            R.id.action_verifyEmailFragment_to_loginFragment
                        )
                    }
                    is COnfirmationState.Failure -> {
                        Toast.makeText(activity, state.error.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("Error ",state.error.toString());
                    }
                    else -> {}
                }
            }
        }
    }
    private fun confirmRegistration(){
        viewModel.confirmer(
            email = safeArgs.email,
            password = safeArgs.password,
            nom=safeArgs.nom,
            prenom = safeArgs.prenom
        )
    }
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentVerifyEmailBinding.inflate(layoutInflater,container,false)
}