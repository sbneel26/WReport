package edu.weather.wreport.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import edu.weather.wreport.domain.model.Post
import edu.weather.wreport.util.Resource
import kotlinx.android.synthetic.main.fragment_item.*
import javax.inject.Inject

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    @Inject lateinit var homeModelFactory: ViewModelProvider.Factory
    @Inject lateinit var homeAdapter: HomeItemListAdapter

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }

        val TAG: String = HomeFragment::class.java.canonicalName
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(edu.weather.wreport.R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        homeViewModel = ViewModelProviders.of(this, ViewModelFactoryUtil().createFor(
//                HomeViewModel(FetchAllPosts(RepositoryImpl(ApiFactory.makeApi(), PostMapper())))))
//                .get(HomeViewModel::class.java)
        homeViewModel = ViewModelProviders.of(this, homeModelFactory).get(HomeViewModel::class.java)
        item_list.layoutManager = LinearLayoutManager(activity)
        //homeAdapter = HomeItemListAdapter(arrayListOf())
        item_list.adapter = homeAdapter
    }

    fun fetchResult(climate: String, location: String) {
        showProgress()
        homeViewModel.fetchAllPost(climate, location).observe(this, Observer<Resource<ArrayList<Post>>> {
            hideProgress()
            updateItemList(it?.data)
        })
    }

    fun refreshList() {
        homeAdapter.clearData()
        homeAdapter.notifyDataSetChanged()
    }

    private fun updateItemList(itemList: ArrayList<Post>?) {
        itemList?.let { homeAdapter.set(it) }
    }

    private fun showProgress() {
        if (activity is ProgressDisplay) {
            (activity as ProgressDisplay).showProgress()
        }
    }

    private fun hideProgress() {
        if (activity is ProgressDisplay) {
            (activity as ProgressDisplay).hideProgress()
        }
    }
}
