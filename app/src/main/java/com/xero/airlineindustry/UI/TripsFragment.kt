package com.xero.airlineindustry.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xero.airlineindustry.R
import com.xero.airlineindustry.ViewModel.TripsViewModel

class TripsFragment : Fragment() {
    private var tripsViewModel: TripsViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        tripsViewModel = ViewModelProviders.of(this).get(TripsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_trips, container, false)
        val textView = root.findViewById<TextView>(R.id.text_dashboard)
        tripsViewModel!!.text
            .observe(viewLifecycleOwner, Observer { s -> textView.text = s })
        return root
    }
}