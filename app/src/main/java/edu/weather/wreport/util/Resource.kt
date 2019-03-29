package  edu.weather.wreport.util

open class Resource<out T>(val status: ResourceState, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(ResourceState.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T?): Resource<T> {
            return Resource(ResourceState.ERROR, null, message)
        }

        fun <T> loading(): Resource<T> {
            return Resource(ResourceState.LOADING, null, null)
        }
    }
}