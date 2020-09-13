package com.xero.airlineindustry.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.xero.airlineindustry.R
import com.xero.airlineindustry.Utils.Constants
import com.xero.airlineindustry.ViewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.concurrent.fixedRateTimer

class HomeFragment : Fragment() {
    private var homeViewModel: HomeViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)




        root.home_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {


            override fun onTabSelected(tab: TabLayout.Tab?) {

                val bundle = Bundle()
                val fragtrip = OneRoundTripFragment()

                if (tab!!.position == 0) {

                    bundle.putString(Constants.TripType, "ONE TRIP")
                    val ft = fragmentManager!!.beginTransaction()
                    fragtrip.arguments = bundle
                    ft.replace(R.id.one_round_trip_fragment, fragtrip)
                    ft.commit()
                } else {
                    bundle.putString(Constants.TripType, "ROUND TRIP")
                    val ft = fragmentManager!!.beginTransaction()
                    fragtrip.arguments = bundle
                    ft.replace(R.id.one_round_trip_fragment, fragtrip)
                    ft.commit()
                }


            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {


            }
        })


        return root
    }
}