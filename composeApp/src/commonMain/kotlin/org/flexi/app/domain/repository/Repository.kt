package org.flexi.app.domain.repository

import org.flexi.app.data.remote.FlexiApiClient
import org.flexi.app.data.repository.FlexiApi
import org.flexi.app.domain.model.books.BooksItem
import org.flexi.app.domain.model.category.Categories
import org.flexi.app.domain.model.products.Products
import org.flexi.app.domain.model.promotions.PromotionsProductsItem
import org.koin.core.annotation.Single

@Single
class Repository : FlexiApi {
    override suspend fun loginUser(email: String, password: String): String {
        return FlexiApiClient.loginUser(email, password)
    }

    override suspend fun signupUser(username: String, email: String, password: String): String {
        return FlexiApiClient.signupUser(username, email, password)
    }

    override suspend fun getProducts(): List<Products> {
        return FlexiApiClient.getProducts()
    }

    override suspend fun getPromotionsProducts(): List<PromotionsProductsItem> {
        return FlexiApiClient.getPromotionsList()
    }

    override suspend fun getCategories(): List<Categories> {
        return FlexiApiClient.getCategoriesList()
    }

    override suspend fun getBooksList(): List<BooksItem> {
        return FlexiApiClient.getBooksList()
    }
}