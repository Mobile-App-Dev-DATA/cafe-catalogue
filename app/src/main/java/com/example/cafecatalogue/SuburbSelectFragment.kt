package com.example.cafecatalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.CheckBox
import android.util.Log

class SuburbSelectFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.suburb_select_fragment, container, false)

        val checkBoxFremantle = view.findViewById<CheckBox>(R.id.checkBox_FREMANTLE)
        val checkboxNedlands = view.findViewById<CheckBox>(R.id.checkBox_NEDLANDS)
        val checkBoxWilson = view.findViewById<CheckBox>(R.id.checkBox_WILSON)
        val checkBoxBentley = view.findViewById<CheckBox>(R.id.checkBox_BENTLEY)

        val updateFilter = {
            val selected = mutableSetOf<Suburb>()
            if (checkBoxFremantle.isChecked) selected.add(Suburb.FREMANTLE)
            if (checkboxNedlands.isChecked) selected.add(Suburb.NEDLANDS)
            if (checkBoxWilson.isChecked) selected.add(Suburb.WILSON)
            if (checkBoxBentley.isChecked) selected.add(Suburb.BENTLEY)

            //debugging nonsense
            val message = if (selected.isEmpty()) {
                "non selected (cafes should all be shown)"
            } else {
                "selected suburbs: ${selected.joinToString(", ")}"
            }
            Log.d("SuburbFilter", message)

            (activity as? MainActivity)?.updateSuburbFilter(selected)
        }

        checkBoxFremantle.setOnCheckedChangeListener { _, _ -> updateFilter() }
        checkboxNedlands.setOnCheckedChangeListener { _, _ -> updateFilter() }
        checkBoxWilson.setOnCheckedChangeListener { _, _ -> updateFilter() }
        checkBoxBentley.setOnCheckedChangeListener { _, _ -> updateFilter() }

        return view
    }
}