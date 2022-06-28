package org.mifos.mobile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import org.mifos.mobile.ui.databinding.FragmentMainScreenBinding
import org.mifos.mobile.ui.databinding.SampleCardBinding
import org.mifos.mobile.ui.util.GenericRecyclerViewAdapter

class MainScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainScreenBinding.inflate(inflater, container, false)

        binding.topAppBar.setOnMenuItemClickListener {
            if(it.itemId == R.id.editTheme)
                requireContext().switchNightMode()
            true
        }

        binding.bottomSheetRecyclerView.adapter = GenericRecyclerViewAdapter(
            items = List(30){ it },
            bindingCreator = { parent -> SampleCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)  },
            itemOnClick = {
                val dialog = MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Sample Dialog")
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setMessage("This is some random long dialog message. Just to show you how it looks")
                    .setPositiveButton("OK"){ dialogInterface, _ -> dialogInterface.dismiss() }
                    .setNegativeButton("Cancel"){ dialogInterface, _ -> dialogInterface.dismiss() }
                    .show()
            },
            itemOnLongClick = lambda@{
                val snackBar = Snackbar.make(binding.root, "Item Long Clicked position $it", Snackbar.LENGTH_SHORT)
                snackBar.setAction("OK"){ snackBar.dismiss() }
                snackBar.show()
                return@lambda true
            }
        )

        return binding.root
    }
}