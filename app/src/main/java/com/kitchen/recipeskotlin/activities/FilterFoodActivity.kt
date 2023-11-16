package com.kitchen.recipeskotlin.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kitchen.recipeskotlin.R
import com.kitchen.recipeskotlin.adapter.FilterFoodAdapter
import com.kitchen.recipeskotlin.data.model.ModelFilter
import com.kitchen.recipeskotlin.data.model.ModelMain
import com.kitchen.recipeskotlin.data.network.Api
import com.kitchen.recipeskotlin.databinding.ActivityFilterFoodBinding
import org.json.JSONException
import org.json.JSONObject
import java.util.*

@Suppress("DEPRECATION")
open class FilterFoodActivity : AppCompatActivity(), FilterFoodAdapter.onSelectData {
    private lateinit var binding : ActivityFilterFoodBinding
    var filterFoodAdapter: FilterFoodAdapter? = null
    var progressDialog: ProgressDialog? = null
    var modelFilter: MutableList<ModelFilter> = ArrayList()
    var modelMain: ModelMain? = null
    var strCategory: String? = null
    var strCategoryDescription: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterFoodBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        binding.toolbarFilter.setTitle(null)
        setSupportActionBar(binding.toolbarFilter)
        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Please Wait")
        progressDialog!!.setMessage("Displaying data ...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setCanceledOnTouchOutside(false)

        binding.rvFilter.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        binding.rvFilter.setHasFixedSize(true)

        modelMain = intent.getSerializableExtra("showFilter") as ModelMain
        if (modelMain != null) {
            strCategory = modelMain!!.strCategory
            strCategoryDescription = modelMain!!.strCategoryDescription

            //Set text
            binding.tvTitle.setText("Food List $strCategory")
            binding.tvDescCategories.setText(strCategoryDescription)

            //Get image background
            Glide.with(this)
                .load(modelMain!!.strCategoryThumb)
                .placeholder(R.drawable.ic_food_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgCategoriesBg)

            //Get image source
            Glide.with(this)
                .load(modelMain!!.strCategoryThumb)
                .placeholder(R.drawable.ic_food_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgCategories)

            //Methods get data
            meal
        }
    }

    private val meal: Unit
        private get() {
            progressDialog!!.show()
            AndroidNetworking.get(Api.Filter)
                .addPathParameter("strCategory", strCategory)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            progressDialog!!.dismiss()
                            val playerArray = response.getJSONArray("meals")
                            for (i in 0 until playerArray.length()) {

                                val temp = playerArray.getJSONObject(i)
                                val dataApi = ModelFilter()
                                dataApi.idMeal = temp.getString("idMeal")
                                dataApi.strMeal = temp.getString("strMeal")
                                dataApi.strMealThumb = temp.getString("strMealThumb")
                                modelFilter.add(dataApi)
                                showFilter()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(this@FilterFoodActivity,
                                "Failed to display data!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        progressDialog!!.dismiss()
                        Toast.makeText(this@FilterFoodActivity,"No internet connection!", Toast.LENGTH_SHORT).show()
                    }
                })
        }

    private fun showFilter() {
        filterFoodAdapter = FilterFoodAdapter(this@FilterFoodActivity, modelFilter, this)
        binding.rvFilter!!.adapter = filterFoodAdapter
    }

    override fun onSelected(modelMain: ModelFilter) {
        val intent = Intent(this@FilterFoodActivity, DetailRecipeActivity::class.java)
        intent.putExtra("detailRecipe", modelMain)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        //Set Transparent Status bar
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val win = activity.window
            val winParams = win.attributes
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }
}