package com.xero.airlineindustry.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.material.datepicker.MaterialDatePicker
import com.xero.airlineindustry.Model.PlaceInputModel
import com.xero.airlineindustry.R
import com.xero.airlineindustry.Repository.OneRoundTripRepository
import com.xero.airlineindustry.Utils.Constants
import com.xero.airlineindustry.Utils.Constants.Companion.TripType
import com.xero.airlineindustry.Utils.Constants.Companion.TripTypeRound
import com.xero.airlineindustry.Utils.Injector
import com.xero.airlineindustry.ViewModel.OneRoundTripViewModel
import kotlinx.android.synthetic.main.fragment_one_round_trip.*
import kotlinx.android.synthetic.main.fragment_one_round_trip.view.*
import kotlinx.android.synthetic.main.fragment_one_round_trip.view.from_dropdown

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneRoundTripFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneRoundTripFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: OneRoundTripViewModel by viewModels {
        Injector.provideViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_one_round_trip, container, false)

        val builder_range = MaterialDatePicker.Builder.dateRangePicker()
        val picker_range = builder_range.build()

        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()

        Log.i(TripType, arguments?.getString(TripType).toString())
        val tripType = arguments?.getString(TripType).toString()

        if (tripType.equals(TripType)) {
            root.departDate.visibility = View.VISIBLE
            root.departInputLayout.setHint("Select Depart Date ")

        }
        if (tripType.equals(TripTypeRound)) {
            root.departDate.visibility = View.VISIBLE
            root.departInputLayout.setHint("Select Depart and Return Date ")
        }

        val placeInputModel = PlaceInputModel("Stockholm")

        val seatArray = resources.getStringArray(R.array.seatArray)
        val seatAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, seatArray)
        root.seat_dropdown.setAdapter(seatAdapter)

        viewModel.calloneRoundTrip(placeInputModel)

        viewModel.oneRoundtripUsingFlow.observe(viewLifecycleOwner) { value ->
            if (value != null && value.size > 0) {
                Log.i("PlaceMessage", "${value.get(0).PlaceName}")
            }

            val placesarray = arrayOfNulls<String>(value.size)

            for (i in value.indices) {
                placesarray[i] = value.get(i).PlaceName
            }
            val adapter_from =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, placesarray)
            root.from_dropdown.setAdapter(adapter_from)

            val adapter_to =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, placesarray)
            root.to_dropdown.setAdapter(adapter_to)

        }

        root.departDate.setOnClickListener {

            if (tripType.equals(TripType)) {
                picker.show(parentFragmentManager, picker.toString())

            }
            if (tripType.equals(TripTypeRound)) {
                picker_range.show(parentFragmentManager, picker_range.toString())
            }


        }

        picker.addOnPositiveButtonClickListener {
            Log.d(
                "DatePicker Activity",
                "Date String = ${picker.headerText}:: Date epoch value = ${it}"
            )
            val slectedDate = picker.headerText
            root.departDate.setText(slectedDate)
        }
        picker_range.addOnPositiveButtonClickListener {
            val slectedDate = picker_range.headerText
            root.departDate.setText(slectedDate)
        }


        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneRoundTripFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneRoundTripFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}

class OneRoundTripViewModelFactory(private val repository: OneRoundTripRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        OneRoundTripViewModel(repository) as T

}