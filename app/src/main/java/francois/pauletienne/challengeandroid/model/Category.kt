package francois.pauletienne.challengeandroid.model

data class Category(
    var id: Int,
    var name: String,
    var subCategories: List<Category>?
)