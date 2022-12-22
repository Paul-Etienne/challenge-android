package francois.pauletienne.challengeandroid.network

import francois.pauletienne.challengeandroid.model.Category
import francois.pauletienne.challengeandroid.util.EntityMapper
import javax.inject.Inject

class CategoryMapper @Inject constructor() : EntityMapper<CategoryObjectResponse, Category> {

    override fun mapFromEntity(entity: CategoryObjectResponse): Category {
        return Category(
            id = entity.id,
            name = entity.name ?: "",
            subCategories = null
        )
    }

    fun mapFromRestResponse(response: CategoriesResponse): List<Category> {
        val baseCategories = response.resources
            .filter { category -> category.parent == null }
            .map { mapFromEntity(it) }

        val subCategories = response.resources
            .filter { category -> category.parent != null }

        baseCategories
            .map { baseCategory ->
                baseCategory.subCategories = subCategories.filter { subCategory -> subCategory.parent?.id == baseCategory.id }.map { mapFromEntity(it) }
            }

        return baseCategories
    }

}