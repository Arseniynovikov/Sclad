package com.example.sklad.ui.product.add

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sklad.MainActivity
import com.example.sklad.R
import com.example.sklad.data.database.AppDatabase
import com.example.sklad.data.repository.InventoryRepositoryImpl
import com.example.sklad.databinding.FragmentProductAddBinding
import com.example.sklad.databinding.FragmentUpdateProductBinding
import com.example.sklad.domain.models.CategoryModel
import com.example.sklad.domain.models.ProductModel
import com.example.sklad.domain.repository.InventoryRepository
import com.example.sklad.domain.usecases.category.AddCategoryUseCase
import com.example.sklad.domain.usecases.category.GetAllCategoryUseCase
import com.example.sklad.domain.usecases.product.AddProductUseCase
import com.example.sklad.domain.usecases.product.UpdateProductUseCase
import com.example.sklad.domain.usecases.warehouse.GetAllWarehousesUseCase
import com.example.sklad.ui.barcode.BarcodeScannerManager
import com.example.sklad.ui.product.update.ProductUpdateFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductAddFragment : Fragment() {

    override fun onResume() {
        super.onResume()

        (activity as? MainActivity)?.findViewById<FloatingActionButton>(R.id.fab)?.hide()
    }

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

    private val addProductUseCase by lazy{
        AddProductUseCase(repository)
    }
    private val addCategoryUseCase by lazy{
        AddCategoryUseCase(repository)
    }
    private val getAllCategoryUseCase by lazy{
        GetAllCategoryUseCase(repository)
    }
    private val getAllWarehousesUseCase by lazy{
        GetAllWarehousesUseCase(repository)
    }

    private val viewModel: ProductAddViewModel by viewModels{
        ProductAddViewModelFactory(
            addProductUseCase,
            addCategoryUseCase,
            getAllCategoryUseCase,
            getAllWarehousesUseCase
        )
    }
    private lateinit var binding: FragmentProductAddBinding
    private lateinit var barcodeScannerManager: BarcodeScannerManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.warehouseLiveData.observe(viewLifecycleOwner) { warehouseList ->
            val categoryNames = warehouseList.map { it.name }
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, categoryNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.warehouseSpinner.adapter = adapter
        }
        viewModel.categoryLiveData.observe(viewLifecycleOwner) { categoryList ->
            val categoryNames = categoryList.map { it.name }
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, categoryNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = adapter
        }

        binding.addCategoryButton.setOnClickListener {
            val name = binding.addCategoryText.text.toString()
            viewModel.addCategory(CategoryModel(0, name))
            binding.addCategoryText.setText("")

            Toast.makeText(requireContext(), "Добавлена категория: $name", Toast.LENGTH_SHORT).show()
        }

        var barcode = ""
        barcodeScannerManager = BarcodeScannerManager(this) { scannedBarcode ->
            if (scannedBarcode != null) {
                barcode = scannedBarcode
                binding.barcodeText.text = barcode
                Toast.makeText(requireContext(), "Штрихкод добавлен", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Сканирование отменено", Toast.LENGTH_SHORT).show()
            }
        }

        binding.editBarcodeButton.setOnClickListener {
            barcodeScannerManager.launchScan()
        }

        binding.saveProductButton.setOnClickListener {
            val name = binding.productNameText.text.toString()
            val quantity = binding.quantityText.text.toString().toInt()
            val barcode = binding.barcodeText.text.toString()
            val description = binding.descriptionText.text.toString()
            val unit = binding.unitText.text.toString()
            val categoryId = binding.categorySpinner.selectedItemPosition + 1
            val warehouseId = binding.warehouseSpinner.selectedItemPosition + 1

            viewModel.addProduct(ProductModel(0, name, barcode, description, quantity, unit, categoryId, warehouseId))

            val action =
                ProductAddFragmentDirections.actionProductAddFragmentToProductListFragment()
            findNavController().navigate(action)
        }
    }
}