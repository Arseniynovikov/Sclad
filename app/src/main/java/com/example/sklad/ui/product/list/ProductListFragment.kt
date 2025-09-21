package com.example.sklad.ui.product.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sklad.MainActivity
import com.example.sklad.R
import com.example.sklad.data.database.AppDatabase
import com.example.sklad.data.repository.InventoryRepositoryImpl
import com.example.sklad.databinding.FragmentProductListBinding
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.repository.InventoryRepository
import com.example.sklad.domain.usecases.category.AddCategoryUseCase
import com.example.sklad.domain.usecases.product.AddProductUseCase
import com.example.sklad.domain.usecases.product.DeleteProductUseCase
import com.example.sklad.domain.usecases.product.GetAllProductsUseCase
import com.example.sklad.domain.usecases.warehouse.AddWarehouseUseCase
import com.example.sklad.ui.adapters.ProductAdapter
import com.example.sklad.ui.barcode.BarcodeScannerManager
import com.example.sklad.ui.OnItemClickListener
import com.example.sklad.ui.SwipeCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductListFragment : Fragment(), OnItemClickListener<ProductModel> {

    private lateinit var binding: FragmentProductListBinding

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

    private val getAllProductsUseCase by lazy {
        GetAllProductsUseCase(repository)
    }
    private val addProductUseCase by lazy {
        AddProductUseCase(repository)
    }
    private val deleteProductUseCase by lazy {
        DeleteProductUseCase(repository)
    }

    private val viewModel: ProductListViewModel by viewModels {
        ProductListViewModelFactory(
            getAllProductsUseCase,
            addProductUseCase,
            deleteProductUseCase
        )
    }

    override fun onResume() {
        super.onResume()

//        (activity as? MainActivity)?.findViewById<FloatingActionButton>(R.id.fab)?.show()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val fab = (activity as? MainActivity)?.findViewById<FloatingActionButton>(R.id.fab)
        Log.d("onViewCreatedFab", "$fab")
        fab?.setOnClickListener {

            val action =
                ProductListFragmentDirections.actionProductListFragmentToProductAddFragment()
            findNavController().navigate(action)
        }


        val adapter = ProductAdapter(this)
        binding.productList.layoutManager = LinearLayoutManager(requireContext())
        binding.productList.adapter = adapter

        lifecycleScope.launch {
            viewModel.products.collectLatest {
                adapter.submitList(it)
            }
        }

        val swipeCallback = SwipeCallback { position, direction ->
            val currentList = adapter.currentList.toMutableList()
            val deleteProduct = currentList[position]
            viewModel.delete(deleteProduct)

            Toast.makeText(requireContext(), "Удалён продукт ${deleteProduct.name}", Toast.LENGTH_LONG).show()
        }
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.productList)



    }

    override fun onItemClick(model: ProductModel) {
        val action =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(model)
        findNavController().navigate(action)
    }
}