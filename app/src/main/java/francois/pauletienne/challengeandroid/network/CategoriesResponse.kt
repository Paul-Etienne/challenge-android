package francois.pauletienne.challengeandroid.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("resources")
    @Expose
    var resources: List<CategoryObjectResponse>,

    @SerializedName("pagination")
    @Expose
    var pagination: PaginationObjectResponse,
)