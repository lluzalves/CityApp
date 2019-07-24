package com.app.daniel.app.citiesapp.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.daniel.app.citiesapp.R
import com.app.daniel.app.citiesapp.base.BaseFragment
import com.daniel.domain.dto.City
import kotlinx.android.synthetic.main.fragment_home.*

class CitiesFragment : BaseFragment(), CitiesMvp {

    lateinit var presenter: CitiesPresenter
    lateinit var cities: List<City>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CitiesPresenter()
        presenter.attachView(this)
        presenter.retrieveFetchedCities()
    }

    override fun getCities(cities: List<City>) {
        this.cities = cities
    }

    override fun onCompleted() {
        val staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        staggeredLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        citiesRecycler.apply {
            layoutManager = staggeredLayoutManager
            setHasFixedSize(true)
            registerForContextMenu(this)
            adapter = CitiesAdapter(cities)
            dismissLoadingState()
        }
    }

    private fun dismissLoadingState() {
        progress.visibility = View.GONE
        citiesRecycler.visibility = View.VISIBLE
    }


}