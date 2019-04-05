package edu.weather.wreport.ui.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import edu.weather.wreport.domain.model.Post
import edu.weather.wreport.ui.base.BaseActivity
import timber.log.Timber
import javax.inject.Inject
import android.widget.*
import edu.weather.wreport.R
import kotlinx.android.synthetic.main.activity_home.*

fun Context.homeActivityIntent(): Intent {
    return Intent(this, HomeActivity::class.java)
}
class HomeActivity : BaseActivity(), AdapterView.OnItemSelectedListener, ProgressDisplay {
    private lateinit var climateSpinner: Spinner
    private lateinit var locationsSpinner: Spinner
    @Inject
    lateinit var homeAdapter: HomeItemListAdapter
    private var homeFragment: HomeFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(edu.weather.wreport.R.layout.activity_home)
        setUpToolBar()
        setUpItemList()
        addDropDownControls()
        button_previous_stats.setOnClickListener {
            if (homeFragment != null) {
                if ( climateSpinner.selectedItem.toString() != "Select Climate"
                && locationsSpinner.selectedItem.toString() != "Select Location") {
                    homeFragment!!.refreshList()
                    homeFragment!!.fetchResult(climateSpinner.selectedItem.toString(), locationsSpinner.selectedItem.toString())
                } else {
                    showAlert("Please select climate and location")
                }
            }
        }
    }

    private fun showAlert(msg: String) {
        AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, null)
                .show()
    }

    private fun updateItemList(itemList: ArrayList<Post>?) {
        Timber.i("updated item called")
        itemList?.let { homeAdapter.set(it) }
    }

    private fun setUpItemList() {
        homeFragment = supportFragmentManager.findFragmentById(edu.weather.wreport.R.id.contentFrame) as? HomeFragment
        if (homeFragment == null) {
            // Create the fragment
            homeFragment = HomeFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(edu.weather.wreport.R.id.contentFrame, homeFragment, HomeFragment.TAG)
                    .commit()
            }
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolbar)
    }

    private fun addDropDownControls() {
        addSpinner1()
        addListenerOnSpinner1ItemSelection()
        addSpinner2()
        addListenerOnSpinner2ItemSelection()
    }

    private fun addSpinner1() {
        climateSpinner = findViewById(edu.weather.wreport.R.id.climate_spinner)
        //Adapter for spinner
        climateSpinner.adapter = ArrayAdapter(this, edu.weather.wreport.R.layout.support_simple_spinner_dropdown_item, getClimateValues())
        // Initialize an array adapter
        val mAdapter: ArrayAdapter<*>? = object : ArrayAdapter<String>(this, edu.weather.wreport.R.layout.support_simple_spinner_dropdown_item, getClimateValues()) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                // Cast the spinner collapsed item (non-popup item) as a text view
                val tv = super.getView(position, convertView, parent) as TextView

                // Set the text color of spinner item
                tv.setTextColor(Color.BLACK)

                // Return the view
                return tv
            }
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                // Cast the drop down items (popup items) as text view
                val tv = super.getDropDownView(position, convertView, parent) as TextView

                // Set the text color of drop down items
                tv.setTextColor(Color.BLACK)

                // If this item is selected item
                if (position == 0) {
                    // Set spinner selected popup item's text color
                    tv.setTextColor(Color.RED)
                }

                // Return the modified view
                return tv
            }
        }
        climateSpinner.adapter = mAdapter
    }

    private fun addListenerOnSpinner1ItemSelection() {
        climateSpinner.setSelection(0,false)
        climateSpinner.onItemSelectedListener = this
    }

    private fun addSpinner2() {
        locationsSpinner = findViewById(edu.weather.wreport.R.id.location_spinner)
        //Adapter for spinner
        locationsSpinner.adapter = ArrayAdapter(this, edu.weather.wreport.R.layout.support_simple_spinner_dropdown_item, getLocationsValues())

        // Initialize an array adapter
        val mAdapter: ArrayAdapter<*>? = object : ArrayAdapter<String>(this, edu.weather.wreport.R.layout.support_simple_spinner_dropdown_item, getLocationsValues()) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                // Cast the spinner collapsed item (non-popup item) as a text view
                val tv = super.getView(position, convertView, parent) as TextView

                // Set the text color of spinner item
                tv.setTextColor(Color.BLACK)

                // Return the view
                return tv
            }
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                // Cast the drop down items (popup items) as text view
                val tv = super.getDropDownView(position, convertView, parent) as TextView

                // Set the text color of drop down items
                tv.setTextColor(Color.BLACK)

//                 If this item is selected item
                if (position == 0) {
                    // Set spinner selected popup item's text color
                    tv.setTextColor(Color.RED)
                }

                // Return the modified view
                return tv
            }
        }
        locationsSpinner.adapter = mAdapter
//        val spinnerArrayAdapter = ArrayAdapter<String>(this, edu.rush.mobile.R.layout.spinner_item, plants)
//        spinnerArrayAdapter.setDropDownViewResource(edu.rush.mobile.R.layout.spinner_item)
//        spinner.setAdapter(spinnerArrayAdapter)
    }

    //
    private fun addListenerOnSpinner2ItemSelection() {
        locationsSpinner.setSelection(0,false)
        locationsSpinner.onItemSelectedListener = this
    }

    private fun getClimateValues() : Array<String> {
        return arrayOf(
                "Select Climate",
                "Min Temperature",
                "Max Temperature",
                "Rainfall(mm)")
    }

    private fun getLocationsValues() : Array<String> {
        return arrayOf(
                "Select Location",
                "UK",
                "England",
                "Scotland",
                "Wales")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        val s = findViewById<Spinner>(edu.weather.wreport.R.id.climate_spinner)
        val a = s.adapter as BaseAdapter
        if (parent == s) {
            a.notifyDataSetChanged()
        }

        val s2 = findViewById<Spinner>(edu.weather.wreport.R.id.location_spinner)
        val a2 = s2.adapter as BaseAdapter
        if (parent == s2) {

            a2.notifyDataSetChanged()
        }
    }

    override fun showProgress() {
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
    }

    override fun hideProgress() {
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
    }
}

internal interface ProgressDisplay {
    fun showProgress()
    fun hideProgress()
}