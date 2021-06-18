package com.vikas.optimumuse.view.productlist

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.vikas.optimumuse.R
import com.vikas.optimumuse.model.Product
import com.vikas.optimumuse.utils.MyViewModelFactory
import com.vikas.optimumuse.view.MainActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductListFragment : Fragment() {
    lateinit var textTimer: TextView
    private var productList = arrayListOf<Product>()
    lateinit var recyclerViewProductList: RecyclerView
    lateinit var adapter: ProductRecyclerViewAdapter
    lateinit var productListViewModel: ProductListViewModel
    private var countDownTimer: CountDownTimer? = null
    private val dateFormat = "dd/MM/yyyy"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_productlist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).fab.visibility = VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productListViewModel =
            ViewModelProvider(
                this,
                MyViewModelFactory(context)
            ).get(ProductListViewModel::class.java)

        productListViewModel.productList.observe(viewLifecycleOwner, {
            productList = it as ArrayList<Product>

            val dtf = SimpleDateFormat(
                dateFormat, Locale.ENGLISH
            )
            productList.sortWith { lhs, rhs ->
                dtf.parse(lhs.expiryPeriod).compareTo(
                    dtf.parse(rhs.expiryPeriod)
                )
            }

            adapter = ProductRecyclerViewAdapter(productList)
            recyclerViewProductList.adapter = adapter
        })
        productListViewModel.getAllProducts()
        recyclerViewProductList = view.findViewById(R.id.recyclerViewList) as RecyclerView
        adapter = ProductRecyclerViewAdapter(productList)
        recyclerViewProductList.adapter = adapter
        textTimer = view.findViewById(R.id.textTimer) as TextView
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        countDownTimer?.cancel()
        if (productList.isNotEmpty()) countDownStart(productList[0].expiryPeriod) else textTimer.text =
            "No Item Found"
    }

    private fun countDownStart(date: String) {
        val dateFormat = SimpleDateFormat(dateFormat)
        val event_date = dateFormat.parse(date)
        val current_date = Date()
        val diff = event_date.time - current_date.time
        countDownTimer = object : CountDownTimer(diff, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val Days = millisUntilFinished / (24 * 60 * 60 * 1000)
                val Hours = millisUntilFinished / (60 * 60 * 1000) % 24
                val Minutes = millisUntilFinished / (60 * 1000) % 60
                val Seconds = millisUntilFinished / 1000 % 60
                //
                val timerString = "${String.format("%02d", Days)} days left"
                textTimer.text = timerString
            }

            override fun onFinish() {
                textTimer.text = "Expired"
            }
        }.start()
    }
}