package com.sweny.suncorp.astronautapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sweny.suncorp.astronautapplication.databinding.ItemAustronautBinding
import com.sweny.suncorp.astronautapplication.databinding.ItemAustronautDetailsBinding
import com.sweny.suncorp.astronautapplication.model.AstronautData
import com.sweny.suncorp.astronautapplication.viewmodels.SelectedData

class AstronautDetailsAdapter(
    private val astronauts: AstronautData,
) :
    RecyclerView.Adapter<AstronautDetailsAdapter.AstronautViewHolder>() {

    inner class AstronautViewHolder(
        private val itemBinding: ItemAustronautDetailsBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: AstronautData) {
                itemBinding.name.text = item.name
                itemBinding.nationality.text = item.nationality
                itemBinding.bio.text = item.bio
                itemBinding.dateOfBirth.text = item.date_of_birth
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AstronautViewHolder(
            ItemAustronautDetailsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AstronautViewHolder, position: Int) =
        holder.bind(astronauts)

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}