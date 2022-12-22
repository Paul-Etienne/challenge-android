package francois.pauletienne.challengeandroid.repository

import francois.pauletienne.challengeandroid.model.Category
import francois.pauletienne.challengeandroid.network.CategoryApi
import francois.pauletienne.challengeandroid.network.CategoryMapper
import francois.pauletienne.challengeandroid.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val categoryApi: CategoryApi,
    private val categoryMapper: CategoryMapper
) {
    suspend fun getCategories(): Flow<DataState<List<Category>>> = flow {
        emit(DataState.Loading)
        try {
            val networkCategories = categoryApi.get()
            val categories = categoryMapper.mapFromRestResponse(networkCategories)

            // TODO Add the categories to a Room database

            emit(DataState.Success(categories))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}