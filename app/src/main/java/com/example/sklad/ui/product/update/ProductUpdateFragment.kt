package com.example.sklad.ui.product.update

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sklad.MainActivity
import com.example.sklad.R
import com.example.sklad.data.database.AppDatabase
import com.example.sklad.data.repository.InventoryRepositoryImpl
import com.example.sklad.databinding.FragmentProductDetailBinding
import com.example.sklad.databinding.FragmentUpdateProductBinding
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.repository.InventoryRepository
import com.example.sklad.domain.usecases.category.GetAllCategoryUseCase
import com.example.sklad.domain.usecases.product.GetAllProductsUseCase
import com.example.sklad.domain.usecases.product.UpdateProductUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import com.example.sklad.ui.barcode.BarcodeScannerManager
import com.example.sklad.ui.product.detail.ProductDetailFragmentArgs
import com.example.sklad.ui.product.detail.ProductDetailViewModel
import com.example.sklad.ui.product.detail.ProductDetailViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.getValue

class ProductUpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateProductBinding
    private lateinit var product: ProductModel

    private lateinit var barcodeScannerManager: BarcodeScannerManager

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

    private val updateProductUseCase by lazy{
        UpdateProductUseCase(repository)
    }
    private val getAllCategoryUseCase by lazy{
        GetAllCategoryUseCase(repository)
    }
    private val getAllWarehousesUseCase by lazy{
        GetAllWarehousesUseCase(repository)
    }

    private val args: ProductDetailFragmentArgs by navArgs()


    private val viewModel: ProductUpdateViewModel by viewModels{
        ProductUpdateViewModelFactory(
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
        binding = FragmentUpdateProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        product = args.product

        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { categoryList ->
            val categoryNames = categoryList.map { it.name }
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, categoryNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = adapter

            binding.categorySpinner.setSelection(product.categoryId - 1)
        }
        viewModel.warehousesLiveData.observe(viewLifecycleOwner) { warehouseList ->
            val warehouseNames = warehouseList.map { it.name }
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, warehouseNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.warehouseSpinner.adapter = adapter

            binding.warehouseSpinner.setSelection(product.warehouseId - 1)
        }



        binding.productNameText.setText(product.name)
        binding.barcodeText.text = product.barcode
        binding.quantityText.setText(product.quantity.toString())
        binding.descriptionText.setText(product.description)
        binding.unitText.setText(product.unit)


        barcodeScannerManager = BarcodeScannerManager(this) { scannedBarcode ->
            if (scannedBarcode != null) {
                product.barcode = scannedBarcode
                binding.barcodeText.text = product.barcode
                Toast.makeText(requireContext(), "Штрихкод был изменён", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Сканирование отменено", Toast.LENGTH_SHORT).show()
            }
        }

        binding.editBarcodeButton.setOnClickListener {
            barcodeScannerManager.launchScan()
        }

        binding.saveProductButton.setOnClickListener {
            product.name = binding.productNameText.text.toString()
            product.quantity = binding.quantityText.text.toString().toInt()
            product.description = binding.descriptionText.text.toString()
            product.unit = binding.unitText.text.toString()
            product.categoryId = binding.categorySpinner.selectedItemPosition + 1
            product.warehouseId = binding.warehouseSpinner.selectedItemPosition + 1
            viewModel.saveUpdate(product)

            val action =
                ProductUpdateFragmentDirections.actionProductUpdateFragmentToProductListFragment()
            findNavController().navigate(action)
        }
    }
}