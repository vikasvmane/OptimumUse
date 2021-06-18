package com.vikas.optimumuse.view.addproduct

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialContainerTransform
import com.vikas.optimumuse.R
import com.vikas.optimumuse.model.Product
import com.vikas.optimumuse.utils.DateFormatters
import com.vikas.optimumuse.utils.MyViewModelFactory
import com.vikas.optimumuse.utils.themeColor
import com.vikas.optimumuse.view.MainActivity
import com.vikas.optimumuse.view.addproduct.InputValidator.isInputValid
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddProductFragment : Fragment() {
    private lateinit var editName: EditText
    private lateinit var textExpiryDate: TextView
    private lateinit var btnDatePicker: Button
    private lateinit var btnSubmit: Button
    private val myCalendar = Calendar.getInstance()
    private lateinit var layoutExpiryDate: Group
    private lateinit var layoutExactDate: Group
    private lateinit var radioGroupExpiryDate: RadioGroup
    private lateinit var spinnerTime: Spinner
    private lateinit var timeAdapter: ArrayAdapter<String>
    var duration = listOf("Days", "Month", "Years")
    private lateinit var addProductViewModel: AddProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addproduct, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addProductViewModel = ViewModelProvider(this, MyViewModelFactory(context))
            .get(AddProductViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).fab.visibility = GONE

        btnSubmit = view.findViewById(R.id.btnSubmit)
        layoutExpiryDate = view.findViewById(R.id.groupExpiryDate)
        layoutExactDate = view.findViewById(R.id.groupExactDate)
        radioGroupExpiryDate = view.findViewById(R.id.radioGroupExpiry)
        editName = view.findViewById(R.id.editTextName)
        textExpiryDate = view.findViewById(R.id.textExpDatePreview)
        btnDatePicker = view.findViewById(R.id.btnExpCalendar)
        spinnerTime = view.findViewById(R.id.spinnerTime)

        setUpInputForm()
        setUpListeners()
    }

    private fun setUpInputForm() {
        activity?.applicationContext?.let {
            timeAdapter = ArrayAdapter(it, android.R.layout.simple_spinner_item, duration)
            timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTime.adapter = timeAdapter
            spinnerTime.setSelection(0)
        }
    }

    private fun setUpListeners() {
        radioGroupExpiryDate.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioExpiry -> {
                    layoutExpiryDate.visibility = VISIBLE
                    layoutExactDate.visibility = GONE
                }
                R.id.radioManufactureDate -> {
                    layoutExpiryDate.visibility = VISIBLE
                    layoutExactDate.visibility = VISIBLE
                }
                R.id.radioExactDays -> {
                    layoutExpiryDate.visibility = GONE
                    layoutExactDate.visibility = VISIBLE
                }
            }
        }
        btnSubmit.setOnClickListener {
            if (isInputValid(
                    productName = editName.text.toString(),
                    expiryDate = textExpiryDate.text.toString()
                )
            ) {
                addProductViewModel.insertProduct(
                    Product(
                        title = editName.text.toString(),
                        expiryPeriod = textExpiryDate.text.toString()
                    )
                )
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Please enter valid input", Toast.LENGTH_SHORT).show()
            }
        }
        btnDatePicker.setOnClickListener {
            context?.let { _context ->
                DatePickerDialog(
                    _context, date, myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]
                ).show()
            }
        }
    }

    private var date =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            textExpiryDate.text = DateFormatters.getCurrentDateInDDMMYYYY(myCalendar)
        }

}