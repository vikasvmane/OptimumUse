package com.vikas.optimumuse.view.productlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vikas.optimumuse.R
import com.vikas.optimumuse.model.Product

class ProductRecyclerViewAdapter(private val dataSet: ArrayList<Product>) : RecyclerView.Adapter<ProductRowViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductRowViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.product_row_item, viewGroup, false)

        return ProductRowViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ProductRowViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val product = dataSet[position]
        viewHolder.textProductName.text = product.title
        viewHolder.textExpiryPeriod.text = product.expiryPeriod
        viewHolder.buttonConsumed.setOnClickListener {
            dataSet.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}

class ProductRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textProductName: TextView = itemView.findViewById(R.id.textItemName)
    val textExpiryPeriod: TextView = itemView.findViewById(R.id.textExpiry)
    val buttonConsumed: Button = itemView.findViewById(R.id.btnConsumed)
}