package com.example.sklad.ui.barcode

import androidx.fragment.app.Fragment
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class BarcodeScannerManager(
    fragment: Fragment,
    private val onResult: (barcode: String?) -> Unit
) {

    private val scanLauncher = fragment.registerForActivityResult(ScanContract()) { result ->
        onResult(result?.contents)
    }


    fun launchScan() {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
            setPrompt("Сканируйте штрихкод")
        }
        scanLauncher.launch(options)
    }
}