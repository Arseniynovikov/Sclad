package com.example.sklad.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sklad.R
import com.example.sklad.data.database.entities.Warehouse
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.models.WarehouseModel
import com.example.sklad.ui.OnItemClickListener

class WarehouseAdapter (
    private val listener: OnItemClickListener<WarehouseModel>
) : ListAdapter<WarehouseModel, WarehouseAdapter.WarehouseViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WarehouseModel>() {
            override fun areItemsTheSame(oldItem: WarehouseModel, newItem: WarehouseModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WarehouseModel, newItem: WarehouseModel): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class WarehouseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val warehouseNameTextView: TextView = itemView.findViewById(R.id.warehouseName)
        private val locationTextView: TextView = itemView.findViewById(R.id.warehouseLocation)


        fun bind(warehouse: WarehouseModel) {
            warehouseNameTextView.text = warehouse.name
            locationTextView.text = warehouse.location

            itemView.setOnClickListener {
                listener.onItemClick(warehouse)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarehouseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_warehouse, parent, false)
        return WarehouseViewHolder(view)
    }


    override fun onBindViewHolder(holder: WarehouseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}