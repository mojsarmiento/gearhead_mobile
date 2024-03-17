package com.example.navigations.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigations.R

class OrdersAdapter(private var orders: List<OrdersRequest>) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    fun updateOrders(newOrders: List<OrdersRequest>) {
        orders = newOrders
        notifyDataSetChanged()
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        private val quantityTextView: TextView = itemView.findViewById(R.id.quantityTextView)
        private val totalAmountTextView: TextView = itemView.findViewById(R.id.totalAmountTextView)
        private val paymentMethodTextView: TextView = itemView.findViewById(R.id.paymentMethodTextView)
        private val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
        //private val productImageView: ImageView = itemView.findViewById(R.id.productImageView)

        fun bind(order: OrdersRequest) {
            productNameTextView.text = order.product_names
            quantityTextView.text = "Quantity: ${order.quantity}"
            totalAmountTextView.text = "Total Amount: ${order.total_amount}"
            paymentMethodTextView.text = "Payment Method: ${order.payment_method}"
            statusTextView.text = "Status: ${order.status}"
            // Load image using Glide or Picasso

           // Glide.with(itemView.context)
              //  .load(order.product_images)
              //  .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
              //  .error(R.drawable.error_image) // Error image if loading fails
              //  .into(productImageView)
        }
    }
}



