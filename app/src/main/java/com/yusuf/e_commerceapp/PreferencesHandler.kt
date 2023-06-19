package com.yusuf.e_commerceapp

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.yusuf.e_commerceapp.model.domain.User


class PreferencesHandler {
    companion object {
        const val PACKAGE_NAME = "com.example.myapp"
        private fun getEditor(context: Context): SharedPreferences.Editor {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                PACKAGE_NAME, Context.MODE_PRIVATE
            )
            return sharedPreferences.edit()
        }

        private fun getSharedPref(context: Context): SharedPreferences {
            return context.getSharedPreferences(
                PACKAGE_NAME, Context.MODE_PRIVATE
            )
        }


        fun setUser(context: Context,user: User?){
            val gson:Gson = Gson()
            if(user==null){
                getEditor(context).putString("user","").commit();
                return
            }
            val json:String = gson.toJson(user)
            getEditor(context).putString("user",json).commit();
        }
        fun getUser(context: Context):User? {
            val gson:Gson = Gson()
            val json: String? = getSharedPref(context).getString("user","")
            if(json==""){
                return null
            }
            val user:User = gson.fromJson(json,User::class.java)
            return user
        }
    }
}
