package com.app.daniel.app.citiesapp.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.customview.customView
import com.app.daniel.app.citiesapp.R
import com.app.daniel.app.citiesapp.dependency.ApplicationDependency
import com.daniel.common.applyScheduler
import com.daniel.common.toEditable
import com.daniel.domain.dto.City
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.cell_city_item.view.*
import kotlinx.android.synthetic.main.city_info.*

class CitiesAdapter constructor(private val cities: List<City>) : RecyclerView.Adapter<CitiesViewHolder>() {

    private lateinit var holder: CitiesViewHolder
    private val useCase = ApplicationDependency.SHARED.retrieveCityUseCase()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_city_item, null)
        holder = CitiesViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.show(cities[position])
        holder.itemView.editCity.setOnClickListener {
            editCityItem(position)
        }
    }

    private fun editCityItem(position: Int) {
        MaterialDialog(holder.itemView.context, BottomSheet(LayoutMode.WRAP_CONTENT))
            .show {
                this.title(R.string.edit_city)
                this.message(R.string.inform_new_city_data)
                this.cornerRadius(16F)
                this.positiveButton {
                    cities[position].code = cityNewCode.text.toString()
                    cities[position].uf = cityNewUf.text.toString()
                    cities[position].name = cityNewName.text.toString()
                    useCase?.buildInsertCityUseCase(cities[position])
                        ?.applyScheduler()
                        ?.subscribeBy(
                            onSuccess = {
                                Snackbar.make(this.view, R.string.updated, Snackbar.LENGTH_SHORT).show()
                                notifyItemChanged(position)
                            },
                            onError = {
                                Snackbar.make(this.view, it.localizedMessage, Snackbar.LENGTH_SHORT).show()
                            }
                        )
                }
                customView(R.layout.city_info)
                cityNewCode.text = cities[position].code.toEditable()
                cityNewUf.text = cities[position].uf.toEditable()
                cityNewName.text = cities[position].name.toEditable()
                setPeekHeight(R.dimen.default_peek_height)
            }
    }
}