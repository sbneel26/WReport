package edu.weather.wreport.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.weather.wreport.R
import edu.weather.wreport.domain.model.Post
import javax.inject.Inject


class  HomeItemListAdapter@Inject constructor() : RecyclerView.Adapter<HomeItemListAdapter.ViewHolder>() {

    private var itemList: List<Post> = arrayListOf()
    fun set(itemList: List<Post>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeItemUIModel = itemList[position]
        holder.userIdText.text = homeItemUIModel.tempId.toString()
        holder.postIdText.text = homeItemUIModel.year.toString()
        holder.bodyText.text = homeItemUIModel.month.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.card_post_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userIdText: TextView = view.findViewById(R.id.tempId)
        val postIdText: TextView = view.findViewById(R.id.yearId)
        var bodyText: TextView = view.findViewById(R.id.month)
    }
}