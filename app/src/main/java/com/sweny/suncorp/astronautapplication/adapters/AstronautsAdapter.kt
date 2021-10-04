package com.sweny.suncorp.astronautapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sweny.suncorp.astronautapplication.databinding.ItemAustronautBinding
import com.sweny.suncorp.astronautapplication.model.AstronautData
import com.sweny.suncorp.astronautapplication.viewmodels.SelectedData

class AstronautsAdapter(
    private val astronauts: List<AstronautData>,
    private val callback: SelectedData? = null
) :
    RecyclerView.Adapter<AstronautsAdapter.AstronautViewHolder>() {

    inner class AstronautViewHolder(
        private val itemBinding: ItemAustronautBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: AstronautData) {
                itemBinding.name.text = item.name
                itemBinding.nationality.text = item.nationality
                itemBinding.tripTile.setOnClickListener { callback?.onSelectData(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AstronautViewHolder(
            ItemAustronautBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AstronautViewHolder, position: Int) =
        holder.bind(astronauts[position])

    override fun getItemCount() = astronauts.size

}