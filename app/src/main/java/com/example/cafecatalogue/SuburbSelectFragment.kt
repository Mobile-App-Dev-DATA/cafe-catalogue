package com.example.cafecatalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.CheckBox
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

class SuburbSelectFragment : Fragment() {

    private val viewModel:SearchVM by activityViewModels()
    private lateinit var checkBoxFremantle:CheckBox
    private lateinit var checkBoxNedlands:CheckBox
    private lateinit var checkBoxMtLawley:CheckBox
    private lateinit var checkBoxBentley:CheckBox


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.suburb_select_fragment, container, false)

        checkBoxFremantle = view.findViewById<CheckBox>(R.id.checkBox_FREMANTLE)
        checkBoxNedlands = view.findViewById<CheckBox>(R.id.checkBox_NEDLANDS)
        checkBoxMtLawley = view.findViewById<CheckBox>(R.id.checkBox_MTLAWLEY)
        checkBoxBentley = view.findViewById<CheckBox>(R.id.checkBox_BENTLEY)


        val updateFilter = {
            val selected = mutableSetOf<Suburb>()
            if (checkBoxFremantle.isChecked) selected.add(Suburb.FREMANTLE)
            if (checkBoxNedlands.isChecked) selected.add(Suburb.NEDLANDS)
            if (checkBoxMtLawley.isChecked) selected.add(Suburb.MTLAWLEY)
            if (checkBoxBentley.isChecked) selected.add(Suburb.BENTLEY)

            //debugging nonsense
            val message = if (selected.isEmpty()) {
                "non selected (cafes should all be shown)"
            } else {
                "selected suburbs: ${selected.joinToString(", ")}"
            }
            Log.d("Suburb select fragment", message)
            viewModel.setSuburbs(selected)
            Log.d("Suburb select fragment","view model data query:${viewModel.query.value} :: suburbs:${viewModel.suburbs.value}")
        }

        checkBoxFremantle.setOnCheckedChangeListener { _, _ -> updateFilter() }
        checkBoxNedlands.setOnCheckedChangeListener { _, _ -> updateFilter() }
        checkBoxMtLawley.setOnCheckedChangeListener { _, _ -> updateFilter() }
        checkBoxBentley.setOnCheckedChangeListener { _, _ -> updateFilter() }

        return view
    }
}