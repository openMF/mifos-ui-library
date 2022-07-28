package org.mifos.mobile.ui.app

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import org.mifos.mobile.ui.app.databinding.FragmentThirdBinding
import java.time.LocalDate
import java.time.Year
import java.util.*

class ThirdFragment : Fragment() {

    var previouslySelectedDate: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentThirdBinding.inflate(inflater, container, false)

        binding.apply {
            profileImage.setOnImageChangeButtonListener{
                Toast.makeText(requireContext(), "Image change button clicked", Toast.LENGTH_SHORT).show()
            }

            dateDialogTrigger.setOnClickListener {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.timeInMillis = MaterialDatePicker.todayInUtcMilliseconds()
                val startConst = calendar.timeInMillis
                calendar[Calendar.DAY_OF_MONTH] = calendar[Calendar.DAY_OF_MONTH] + 10
                val endConst = calendar.timeInMillis

                val constraints: CalendarConstraints = CalendarConstraints.Builder()
                    .setOpenAt(previouslySelectedDate ?: MaterialDatePicker.todayInUtcMilliseconds())
                    .setValidator(DateValidatorPointForward.now())
                    .build()

                val dialog = MaterialDatePicker
                    .Builder
                    .datePicker()
                    .setCalendarConstraints(constraints)
                    .setTitleText("Select payment date")
                    .setSelection(previouslySelectedDate ?: MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
                dialog.addOnPositiveButtonClickListener { previouslySelectedDate = it }
                dialog.show(parentFragmentManager, "DATE_PICKER")
            }
        }

        return binding.root
    }

}