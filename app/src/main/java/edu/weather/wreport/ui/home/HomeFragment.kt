package edu.rush.myrush.ui.home

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
import edu.weather.wreport.R
import edu.weather.wreport.domain.model.Post
import edu.weather.wreport.ui.home.HomeItemListAdapter
import edu.weather.wreport.ui.home.HomeViewModel
import edu.weather.wreport.util.Resource
import kotlinx.android.synthetic.main.fragment_item.*
import timber.log.Timber
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
        return inflater!!.inflate(R.layout.fragment_item, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        Timber.i("View Created Called again")
        super.onViewCreated(view, savedInstanceState)
//        homeViewModel = ViewModelProviders.of(this, ViewModelFactoryUtil().createFor(
//                HomeViewModel(FetchAllPosts(RepositoryImpl(ApiFactory.makeApi(), PostMapper())))))
//                .get(HomeViewModel::class.java)
        homeViewModel = ViewModelProviders.of(this, homeModelFactory).get(HomeViewModel::class.java)
        item_list.layoutManager = LinearLayoutManager(activity)
        //homeAdapter = HomeItemListAdapter(arrayListOf())
        item_list.adapter = homeAdapter
        homeViewModel.fetchAllPost().observe(this, Observer<Resource<List<Post>>> {
            Timber.i("Post size ::" + it?.data?.size)
            updateItemList(it?.data)
        })
    }

    private fun updateItemList(itemList: List<Post>?) {
        Timber.i("updated item called")
        itemList?.let { homeAdapter.set(it) }
    }
}
