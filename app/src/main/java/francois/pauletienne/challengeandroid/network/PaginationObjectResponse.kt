package francois.pauletienne.challengeandroid.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PaginationObjectResponse(
    @SerializedName("previous_uri")
    @Expose
    var previousUri: String?,

    @SerializedName("next_uri")
    @Expose
    var nextUri: String?
)