package francois.pauletienne.challengeandroid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import francois.pauletienne.challengeandroid.network.CategoryApi
import francois.pauletienne.challengeandroid.network.CategoryMapper
import francois.pauletienne.challengeandroid.repository.MainRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMainRepository(
        categoryApi: CategoryApi,
        categoryMapper: CategoryMapper
    ): MainRepository {
        return MainRepository(categoryApi, categoryMapper)
    }
}