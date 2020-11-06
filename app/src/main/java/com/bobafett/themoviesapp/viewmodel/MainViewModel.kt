package com.bobafett.themoviesapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.util.*

class MainViewModel : ViewModel() {

    var job : Job? = null
    var scope = MainScope()

    private val name: MutableLiveData<UIEvent> = MutableLiveData()
    val nameStream: LiveData<UIEvent> = name

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
        Log.d("Coroutine", "cleared")
    }

    fun doLogin(username: String, password: String) {
        return if (username == "javier")
            name.postValue(UIEvent.success("authorized", 18))
        else
            name.postValue(UIEvent.failed(500, "Server Error"))
    }

    fun getCoroutine() {
        viewModelScope.launch(Dispatchers.IO) {
            ProfileManager.isUserLoggedIn()
            val data: UserData = getUserData()
        }

        var hola = ProductList(listOf(Product(1.0),Product(1.0),Product(1.0),Product(1.0)))
    }

    sealed class UIEvent {
        data class success(val name: String, val age: Int) : UIEvent()
        data class failed(val errorCode: Int, val errorMessage: String): UIEvent()
        data class create_account(val name: String, val age: Int, val dateOfCreation: Date): UIEvent()
    }

    class ProfileManager {
        companion object {
            fun isUserLoggedIn(): Boolean {
                return true
            }
        }
    }

    fun ProfileManager.isUserLoggedIn(): Boolean {
        return false
    }


}

class Product(var price: Double) {

}

class ProductList<T: Product>(var products: List<T>) {
    fun getProducts() {

    }
}

suspend fun getUserData(): UserData = withContext(Dispatchers.IO) {
    // do expensive work
    UserData()
}

class UserData()