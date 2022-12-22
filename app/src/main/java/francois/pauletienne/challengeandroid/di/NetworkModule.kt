package francois.pauletienne.challengeandroid.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import francois.pauletienne.challengeandroid.network.CategoryApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/bankin-engineering/challenge-android/master/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    fun provideCategoryService(retrofit: Retrofit.Builder): CategoryApi {
        return retrofit
            .build()
            .create(CategoryApi::class.java)
    }
}