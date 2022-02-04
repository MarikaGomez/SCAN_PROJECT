package com.coding.scanproject.scanMeal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.coding.scanproject.mealDetail.DetailMealActivity
import com.coding.scanproject.mealList.MealsAdapter
import com.coding.scanproject.mealList.MealsListViewModel
import com.coding.scanproject.mealList.MealsListViewModelFactory
import com.coding.scanproject.application.MealsApplication
import com.coding.scanproject.configDatabase.MealsDbApi
import com.coding.scanproject.entity.MealsData
import com.coding.scanproject.entity.MealsWrapper
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MealsScanActivity : AppCompatActivity() {
    private lateinit var mQrResultLauncher : ActivityResultLauncher<Intent>

    private val viewModel: MealsListViewModel by viewModels{ MealsListViewModelFactory((application as MealsApplication).repository) }
    private lateinit var adapter: MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, DetailMealActivity::class.java)

        // Alternative to "onActivityResult", because that is "deprecated"
        mQrResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK) {
                val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)

                if(result.contents != null) {
                    // get value of the param "i" from GET request
                    var uri: Uri = Uri.parse(result.contents)
                    var id: String? = uri.getQueryParameter("i")


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
                            val wrapping : MealsWrapper? = response.body()
                            val dataList : List<MealsData> = wrapping!!.meals
                            val meal : MealsData = dataList[0]
                            viewModel.insertMeal(meal)
                            startActivity(intent)
                        }

                        // response != 200 | connection problem
                        override fun onFailure(call: Call<MealsWrapper>, t: Throwable) {
                            Log.e("MainActivity", "onFailure: ",t )
                        }
                    })
                }
            }
        }
        startScanner()
    }

    // Start the QR Scanner
    private fun startScanner() {
        val scanner = IntentIntegrator(this)
        // QR Code Format
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        // Set Text Prompt at Bottom of QR code Scanner Activity
        scanner.setPrompt("Scan Your QR Code")
        // Start Scanner (don't use initiateScan() unless if you want to use OnActivityResult)
        mQrResultLauncher.launch(scanner.createScanIntent())
    }
}