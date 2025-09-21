package com.example.sklad.ui.product.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sklad.MainActivity
import com.example.sklad.R
import com.example.sklad.data.database.AppDatabase
import com.example.sklad.data.repository.InventoryRepositoryImpl
import com.example.sklad.databinding.FragmentProductDetailBinding
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.repository.InventoryRepository
import com.example.sklad.domain.usecases.category.GetAllCategoryUseCase
import com.example.sklad.domain.usecases.product.UpdateProductUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var product: ProductModel


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

    private val updateProductUseCase by lazy {
        UpdateProductUseCase(repository)
    }
    private val getAllWarehousesUseCase by lazy {
        GetAllWarehousesUseCase(repository)
    }
    private val getAllCategoryUseCase by lazy {
        GetAllCategoryUseCase(repository)
    }

    private val args: ProductDetailFragmentArgs by navArgs()

    private val viewModel: ProductDetailViewModel by viewModels {
        ProductDetailViewModelFactory(
            updateProductUseCase,
            getAllCategoryUseCase,
            getAllWarehousesUseCase
        )
    }

    override fun onResume() {
        super.onResume()

        (activity as? MainActivity)?.findViewById<FloatingActionButton>(R.id.fab)?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = args.product

        viewModel.categoryLiveData.observe(viewLifecycleOwner) { categoryList ->
            binding.categoryText.text = categoryList[product.categoryId - 1].name

        }
        viewModel.warehouseLiveData.observe(viewLifecycleOwner) { warehouseList ->
            binding.warehouseText.text = warehouseList[product.warehouseId - 1].name

        }

        binding.productNameText.text = product.name
        binding.barcodeText.text = product.barcode
        binding.quantityText.text = product.quantity.toString()
        binding.descriptionText.text = product.description
        binding.unitText.text = product.unit

        binding.incQuantityButton.setOnClickListener {
            product.quantity += 1
            binding.quantityText.text = product.quantity.toString()
            viewModel.incQuantity(product)
        }

        binding.decQuantityButton.setOnClickListener {
            if (product.quantity > 0) {
                product.quantity -= 1
                binding.quantityText.text = product.quantity.toString()
                viewModel.decQuantity(product)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Количество не может быть ниже 0",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.editProductButton.setOnClickListener {
            val action =
                ProductDetailFragmentDirections.actionProductDetailFragmentToProductUpdateFragment(
                    product
                )
            findNavController().navigate(action)
        }
    }
}