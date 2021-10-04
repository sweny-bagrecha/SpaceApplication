package com.sweny.suncorp.astronautapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweny.suncorp.astronautapplication.databinding.FragmentAstronautsBinding
import com.sweny.suncorp.astronautapplication.model.AstronautData
import com.sweny.suncorp.astronautapplication.viewmodels.BaseTripViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.sweny.suncorp.astronautapplication.R
import com.sweny.suncorp.astronautapplication.adapters.AstronautDetailsAdapter
import com.sweny.suncorp.astronautapplication.utils.showAlertDialog
import com.sweny.suncorp.astronautapplication.viewmodels.AstronautDetailsViewModel

class AstronautDetailsFragment : BaseTripFragment() {
    private val viewModel: AstronautDetailsViewModel by viewModels()

    // baseViewModel is being observed by base fragment to handle errors
    override val baseViewModel: BaseTripViewModel
        get() = viewModel

    private var mAstronauts :AstronautData? = null
    private lateinit var binding: FragmentAstronautsBinding

    private val args: AstronautDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAstronautsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.astronautDetails.observe(viewLifecycleOwner, Observer { itemDetail ->

            mAstronauts = itemDetail
            setupRv(binding.rvSpaceAstronauts, itemDetail)
        })

        viewModel.apiError.observe(viewLifecycleOwner, {
            showAlertDialog(
                requireContext(),
                getString(R.string.error),
                it,
                getString(R.string.ok_got_it)
            )
        })


    }

    private fun setupRv(rv: RecyclerView, astronauts: AstronautData) {
        with(rv) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = AstronautDetailsAdapter(astronauts)
        }
    }

    override fun onResume() {
        viewModel.getAstronautDetails(args.astronautId)
        super.onResume()
    }

}
