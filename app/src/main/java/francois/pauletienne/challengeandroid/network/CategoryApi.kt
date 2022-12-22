package francois.pauletienne.challengeandroid.network

import retrofit2.http.GET

interface CategoryApi {
    @GET("categories.json")
    suspend fun get(): CategoriesResponse
}