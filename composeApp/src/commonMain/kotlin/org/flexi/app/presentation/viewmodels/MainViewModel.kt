package org.flexi.app.presentation.viewmodels

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.flexi.app.domain.model.books.BooksItem
import org.flexi.app.domain.model.category.Categories
import org.flexi.app.domain.model.products.Products
import org.flexi.app.domain.model.promotions.PromotionsProductsItem
import org.flexi.app.domain.repository.Repository
import org.flexi.app.domain.usecase.ResultState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _login = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val login: StateFlow<ResultState<String>> = _login.asStateFlow()

    private val _signup = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val signup = _signup.asStateFlow()

    private val _products = MutableStateFlow<ResultState<List<Products>>>(ResultState.Loading)
    val products: StateFlow<ResultState<List<Products>>> = _products.asStateFlow()

    private val _promotions =
        MutableStateFlow<ResultState<List<PromotionsProductsItem>>>(ResultState.Loading)
    val promotions: StateFlow<ResultState<List<PromotionsProductsItem>>> = _promotions.asStateFlow()

    private val _categories = MutableStateFlow<ResultState<List<Categories>>>(ResultState.Loading)
    val categories: StateFlow<ResultState<List<Categories>>> = _categories.asStateFlow()

    private val _books = MutableStateFlow<ResultState<List<BooksItem>>>(ResultState.Loading)
    val books: StateFlow<ResultState<List<BooksItem>>> = _books.asStateFlow()
    fun getBooksList() {
        viewModelScope.launch {
            _books.value = ResultState.Loading
            try {
                val response = repository.getBooksList()
                _books.value = ResultState.Success(response)
            } catch (e: Exception) {
                _books.value = ResultState.Error(e)
            }
        }
    }

    fun getCategoriesList() {
        viewModelScope.launch {
            _categories.value = ResultState.Loading
            try {
                val response = repository.getCategories()
                _categories.value = ResultState.Success(response)
            } catch (e: Exception) {
                _categories.value = ResultState.Error(e)
            }
        }
    }

    fun getPromotionsItems() {
        viewModelScope.launch {
            _promotions.value = ResultState.Loading
            try {
                val response = repository.getPromotionsProducts()
                _promotions.value = ResultState.Success(response)
            } catch (e: Exception) {
                _promotions.value = ResultState.Error(e)
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.loginUser(email, password)
                _login.value = ResultState.Success(response)
            } catch (e: Exception) {
                _login.value = ResultState.Error(e)
            }
        }
    }

    fun signupUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.signupUser(username, email, password)
                _signup.value = ResultState.Success(response)
            } catch (e: Exception) {
                _signup.value = ResultState.Error(e)
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            _products.value = ResultState.Loading
            try {
                val response = repository.getProducts()
                _products.value = ResultState.Success(response)
            } catch (e: Exception) {
                _products.value = ResultState.Error(e)
            }
        }
    }

}