package com.sweny.suncorp.astronautapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.sweny.suncorp.astronautapplication.R
import com.sweny.suncorp.astronautapplication.databinding.ItemAustronautDetailsBinding
import com.sweny.suncorp.astronautapplication.model.AstronautData

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

                itemBinding.profileImage.load(item.profile_image) {

                    placeholder(R.drawable.outline_person_24)
                    crossfade(750)
                    scale(Scale.FILL)
                    error(R.drawable.outline_person_24)

                    build()
            }
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

    override fun getItemCount(): Int = 1

}