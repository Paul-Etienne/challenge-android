package francois.pauletienne.challengeandroid.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryObjectResponse(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("resource_uri")
    @Expose
    var resourceUri: String,

    @SerializedName("resource_type")
    @Expose
    var resourceType: String,

    @SerializedName("name")
    @Expose
    var name: String?,

    @SerializedName("parent")
    @Expose
    var parent: CategoryObjectResponse?,

    @SerializedName("custom")
    @Expose
    var custom: Boolean?,

    @SerializedName("other")
    @Expose
    var other: Boolean?,

    @SerializedName("is_deleted")
    @Expose
    var isDeleted: Boolean?,
)