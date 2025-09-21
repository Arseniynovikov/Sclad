package com.example.sklad.ui.warehouse.list

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sklad.MainActivity
import com.example.sklad.R
import com.example.sklad.data.database.AppDatabase
import com.example.sklad.data.repository.InventoryRepositoryImpl
import com.example.sklad.databinding.FragmentProductListBinding
import com.example.sklad.databinding.FragmentWarehouseListBinding
import com.example.sklad.domain.models.WarehouseModel
import com.example.sklad.domain.repository.InventoryRepository
import com.example.sklad.domain.usecases.product.AddProductUseCase
import com.example.sklad.domain.usecases.product.DeleteProductUseCase
import com.example.sklad.domain.usecases.product.GetAllProductsUseCase
import com.example.sklad.domain.usecases.warehouse.AddWarehouseUseCase
import com.example.sklad.domain.usecases.warehouse.DeleteWarehouseUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import com.example.sklad.ui.OnItemClickListener
import com.example.sklad.ui.SwipeCallback
import com.example.sklad.ui.adapters.ProductAdapter
import com.example.sklad.ui.adapters.WarehouseAdapter
import com.example.sklad.ui.product.list.ProductListFragmentDirections
import com.example.sklad.ui.product.list.ProductListViewModel
import com.example.sklad.ui.product.list.ProductListViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WarehouseListFragment : Fragment(), OnItemClickListener<WarehouseModel> {

    private lateinit var binding: FragmentWarehouseListBinding


    private val database: AppDatabase by lazy {
        AppDatabase.Companion.getDatabase(requireContext())
    }

    private val repository: InventoryRepository by lazy {
        InventoryRepositoryImpl(
            database.productDao(),
            database.categoryDao(),
            database.warehouseDao()
        )
    }

    private val getAllWarehousesUseCase by lazy {
        GetAllWarehousesUseCase(repository)
    }
    private val addWarehouseUseCase by lazy {
        AddWarehouseUseCase(repository)
    }
    private val deleteWarehouseUseCase by lazy {
        DeleteWarehouseUseCase(repository)
    }

    private val viewModel: WarehouseListViewModel by viewModels {
        WarehouseListViewModelFactory(
            getAllWarehousesUseCase,
            addWarehouseUseCase,
            deleteWarehouseUseCase
        )
    }

    override fun onResume() {
        super.onResume()

        (activity as? MainActivity)?.findViewById<FloatingActionButton>(R.id.fab)?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWarehouseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab = (activity as? MainActivity)?.findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener {
            val action =
                WarehouseListFragmentDirections.actionWarehouseListFragmentToWarehouseAddFragment()
            findNavController().navigate(action)
        }

        val adapter = WarehouseAdapter(this)
        binding.warehouseList.layoutManager = LinearLayoutManager(requireContext())
        binding.warehouseList.adapter = adapter

        lifecycleScope.launch {
            viewModel.warehouses.collectLatest {
                adapter.submitList(it)
            }
        }

        val swipeCallback = SwipeCallback { position, direction ->
            val currentList = adapter.currentList.toMutableList()
            val deleteWarehouse = currentList[position]
            viewModel.deleteWarehouse(deleteWarehouse)

            Toast.makeText(requireContext(), "Удалён склад ${deleteWarehouse.name}", Toast.LENGTH_LONG).show()
        }
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.warehouseList)
    }

    override fun onItemClick(model: WarehouseModel) {
        val action =
            WarehouseListFragmentDirections.actionWarehouseListFragmentToWarehouseDetailFragment(model)
        findNavController().navigate(action)
    }
}