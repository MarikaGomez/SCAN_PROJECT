package com.coding.scanproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.coding.scanproject.entity.MealsWrapper
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class ScannerActivity : AppCompatActivity() {
    private lateinit var mQrResultLauncher : ActivityResultLauncher<Intent>
    private lateinit var binding: ScannerActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Alternative to "onActivityResult", because that is "deprecated"
        mQrResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK) {
                val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)

                if(result.contents != null) {
                    // Do something with the contents (this is usually a URL)
                    Log.i("MainActivity", result.contents)
                    // get id from get request
                    var uri: Uri = Uri.parse(result.contents)
                    var id: String? = uri.getQueryParameter("i")

                    Log.i("MainActivityID", "$id")

                    val retrofit: Retrofit = Retrofit.Builder()
                        // server url
                        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                        // convert to JSON wish MOSHI
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build()

                    // call the interface
                    val api = retrofit.create(MealsDbApi::class.java)

                    val call = api.getRecipeData(id)

                    call.enqueue(object : Callback<MealsWrapper> {
                        override fun onResponse(
                            // check the response | 200
                            call: Call<MealsWrapper>,
                            response: Response<MealsWrapper>
                        ) {
                            Log.i("MainActivity", "" + response.body())
                        }

                        // response != 200 | connection problem
                        override fun onFailure(call: Call<MealsWrapper>, t: Throwable) {
                            Log.e("MainActivity", "onFailure: ",t )
                        }

                    })
                    Log.i("MainActivity", retrofit.toString())
                }
            }
        }

        // Starts scanner on Create of Overlay (you can also call this function using a button click)
        startScanner()
    }


    // Start the QR Scanner
    private fun startScanner() {
        val scanner = IntentIntegrator(this)
        // QR Code Format
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        // Set Text Prompt at Bottom of QR code Scanner Activity
        scanner.setPrompt("QR Code Scanner Prompt Text")
        // Start Scanner (don't use initiateScan() unless if you want to use OnActivityResult)
        mQrResultLauncher.launch(scanner.createScanIntent())
    }
}