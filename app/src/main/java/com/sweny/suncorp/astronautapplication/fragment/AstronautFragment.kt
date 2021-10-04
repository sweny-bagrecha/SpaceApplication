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
import com.sweny.suncorp.astronautapplication.viewmodels.AstronautsViewModel
import com.sweny.suncorp.astronautapplication.viewmodels.BaseTripViewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sweny.suncorp.astronautapplication.R
import com.sweny.suncorp.astronautapplication.adapters.AstronautsAdapter
import com.sweny.suncorp.astronautapplication.utils.showAlertDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AstronautFragment : BaseTripFragment() {
    private val viewModel: AstronautsViewModel by viewModels()

    // baseViewModel is being observed by base fragment to handle errors
    override val baseViewModel: BaseTripViewModel
        get() = viewModel

    private var mAstronauts: List<AstronautData>? = null
    private lateinit var binding: FragmentAstronautsBinding

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
        viewModel.astronautsList.observe(viewLifecycleOwner, Observer { items ->

            mAstronauts = items
            setupRv(binding.rvSpaceAstronauts, items)
        })

        viewModel.apiError.observe(viewLifecycleOwner, {
            showAlertDialog(
                requireContext(),
                getString(R.string.error),
                it,
                getString(R.string.ok_got_it)
            )
        })

        viewModel.eventTripSelected.observe(viewLifecycleOwner, { astronautId ->
            gotoAstronautDetails(astronautId)
        })

    }

    private fun gotoAstronautDetails(astronautId: String) {
        val action =
            AstronautFragmentDirections.actionAstronautslistfragmentToAstronautdetailsfragment(
                astronautId
            )
        findNavController().navigate(action)
    }

    private fun setupRv(rv: RecyclerView, astronauts: List<AstronautData>) {
        with(rv) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = AstronautsAdapter(astronauts, viewModel)
        }
    }

    override fun onResume() {
        viewModel.refreshTrips()
        super.onResume()
    }

}
