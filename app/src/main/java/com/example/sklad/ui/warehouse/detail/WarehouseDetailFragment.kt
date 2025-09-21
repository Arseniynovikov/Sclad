package com.example.sklad.ui.warehouse.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sklad.R

class WarehouseDetailFragment : Fragment() {

    companion object {
        fun newInstance() = WarehouseDetailFragment()
    }

    private val viewModel: WarehouseDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_warehouse_detail, container, false)
    }
}