package org.mifos.mobile.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import org.mifos.mobile.ui.app.R
import org.mifos.mobile.ui.app.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.topAppBar.setOnMenuItemClickListener {
            if(it.itemId == R.id.editTheme)
                requireContext().switchNightMode()
            true
        }

        (binding.dropDown.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(
            arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        )

        return binding.root
    }
}