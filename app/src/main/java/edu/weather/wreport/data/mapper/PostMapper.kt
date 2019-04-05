package edu.weather.wreport.data.mapper

import edu.weather.wreport.data.remote.model.ApiPost
import edu.weather.wreport.domain.model.Post
import io.reactivex.Single
import io.reactivex.functions.Function

class PostMapper : Function<ArrayList<ApiPost>, Single<ArrayList<Post>>> {
    override fun apply(t: ArrayList<ApiPost>): Single<ArrayList<Post>> {
        val newArray = reverse(t)
        val postList = newArray.map {
            val actualMonth = when {
                it.month == 1 -> "Jan"
                it.month == 2 -> "Feb"
                it.month == 3 -> "Mar"
                it.month == 4 -> "Apr"
                it.month == 5 -> "May"
                it.month == 6 -> "Jun"
                it.month == 7 -> "Jul"
                it.month == 8 -> "Aug"
                it.month == 9 -> "Sep"
                it.month == 10 -> "Oct"
                it.month == 11 -> "Nov"
                it.month == 12 -> "Dec"
                else -> {
                    "Jan"
                }
            }
            Post(it.value, it.year, actualMonth)
        }
        val postArrayList: ArrayList<Post> = ArrayList(postList.size)
        postArrayList.addAll(postList)
        return Single.just(postArrayList)
    }

    private fun reverse(list: ArrayList<ApiPost>): ArrayList<ApiPost> {
        for (i in 0 until list.size / 2) {
            val temp = list[i]
            list[i] = list[list.size - i - 1]
            list[list.size - i - 1] = temp
        }
        return list
    }
}