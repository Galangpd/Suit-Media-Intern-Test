package com.example.suitmedia.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.suitmedia.data.retrofit.ApiConfig
import com.example.suitmedia.data.response.UsersItem
import com.example.suitmedia.data.response.UserResponse
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class ThirdScreenViewModel : ViewModel() {
    private val _listUser = MutableLiveData<List<UsersItem>>()
    val listUser: LiveData<List<UsersItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "ThirdScreenViewModel"
    }
    init {
        findUser()
    }

    private fun findUser() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers(1, 10)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.data
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}