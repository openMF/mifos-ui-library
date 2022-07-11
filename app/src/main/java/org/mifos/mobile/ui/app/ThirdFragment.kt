package org.mifos.mobile.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.mifos.mobile.ui.app.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentThirdBinding.inflate(inflater, container, false)
        binding.profileImage.setOnImageChangeButtonListener{
            Toast.makeText(requireContext(), "Image change button clicked", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

}