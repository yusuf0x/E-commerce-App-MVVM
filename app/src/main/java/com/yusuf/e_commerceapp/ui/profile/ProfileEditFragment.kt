package com.yusuf.e_commerceapp.ui.profile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yusuf.e_commerceapp.PreferencesHandler
import com.yusuf.e_commerceapp.core.BaseFragment
import com.yusuf.e_commerceapp.databinding.FragmentProfileEditBinding
import com.yusuf.e_commerceapp.hilt.service.ProductsViewModel
import com.yusuf.e_commerceapp.hilt.service.UpdateProfileState
import com.yusuf.e_commerceapp.model.domain.User
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File


@AndroidEntryPoint
class ProfileEditFragment : BaseFragment<FragmentProfileEditBinding>() {
    val REQUEST_CODE = 200
    private val viewModel: ProductsViewModel by viewModels()
    var pickedPhoto : Uri? = null
    var pickedBitMap : Bitmap? = null
    var encodedImage:String?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user1: User? = PreferencesHandler.getUser(requireActivity())
        binding.apply {
            binding.editTextProfeditFitstname.setText (user1!!.firstname)
            binding.editTextProfeditLastname.setText (user1.lastname)
            binding.editTextProfeditEmail.setText (user1.email)
        }

        binding.updateProfileBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.editImage.setOnClickListener {
            pickPhoto()
        }
        binding.buttonProfUpdate.setOnClickListener {
            update()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.updateProfileState.collect{ state->
                when(state){
                    is UpdateProfileState.Success -> {
                        val user = state.response.utilisteur

                        PreferencesHandler.setUser(requireContext(),User(
                            id = user.id,
                            firstname = user.firstname,
                            lastname = user.lastname,
                            email = user.email,
                            password = user.password,
                            birthday = user.birthday,
                            created_at = user.created_at,
                            updated_at = user.updated_at,
                            role = user.role,
                            image = user.image.toString(),
                            remember_token = user.remember_token
                        ))
                        Toast.makeText(requireActivity(), state.response.message, Toast.LENGTH_SHORT).show()
                    }
                    is UpdateProfileState.Failure -> {
                        Toast.makeText(activity, state.error.toString(), Toast.LENGTH_SHORT).show()
//                        Log.d("Error ",state.error.toString());
//                        Log.d("Image",getRealPathFromURI(imageUri!!).toString())
                    }
                    else -> {}
                }
            }
        }

    }
    private fun getRealPathFromURI(contentURI: Uri): String? {
        val result: String?
        val cursor: Cursor? = requireActivity().contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            result = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }
    private fun update( ) {
        val data= JSONObject()
        if(binding.editTextProfeditFitstname.text!!.length>1){
            data.put("nom", binding.editTextProfeditFitstname.text)
        }
        if(binding.editTextProfeditLastname.text!!.length>1){
            data.put("prenom", binding.editTextProfeditLastname.text)
        }
        if(binding.editTextProfeditEmail.text!!.length>1){
            data.put("email", binding.editTextProfeditEmail.text)
        }
        if(binding.editTextProfeditPassword.text!!.length>1){
            data.put("password", binding.editTextProfeditPassword.text)
        }
        if(encodedImage!=null){
            data.put("image",encodedImage)
        }
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body: RequestBody =  data.toString().toRequestBody(
            mediaType
        )
        viewModel.updateProfile(
            PreferencesHandler.getUser(requireContext())!!.id,
            body
        )
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentProfileEditBinding.inflate(layoutInflater, container, false)

    private fun pickPhoto(){
        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1)
        } else {
            val galeriIntext = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriIntext,2)
        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galeriIntext = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntext,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            pickedPhoto = data.data
            binding.imageProfile.setImageURI(pickedPhoto)
            if (pickedPhoto != null) {
                    val source = ImageDecoder.createSource(requireActivity().contentResolver,pickedPhoto!!)
                    pickedBitMap = ImageDecoder.decodeBitmap(source)
                    encodedImage = encodeToBase64(pickedBitMap!!, Bitmap.CompressFormat.JPEG, 100)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    fun encodeToBase64(image: Bitmap, compressFormat: Bitmap.CompressFormat?, quality: Int): String? {
        val byteArrayOS = ByteArrayOutputStream()
        image.compress(compressFormat, quality, byteArrayOS)
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT)
    }
}