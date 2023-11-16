package com.kitchen.recipes.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kitchen.recipes.R
import com.kitchen.recipes.databinding.ActivityDetailRecipeBinding
import com.kitchen.recipes.data.model.ModelDetailRecipe
import com.kitchen.recipes.data.model.ModelFilter
import com.kitchen.recipes.data.network.Api
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class DetailRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRecipeBinding
    var idMeal: String? = null
    var strMeal: String? = null
    var modelFilter: ModelFilter? = null
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
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

        binding.toolbarDetail.title = null
        setSupportActionBar(binding.toolbarDetail)
        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Please Wait")
        progressDialog!!.setMessage("Displaying data ...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setCanceledOnTouchOutside(false)

        //Button Favorite
        binding.imgFavorite!!.setOnClickListener {
            Toast.makeText(this@DetailRecipeActivity, "Feature under development", Toast.LENGTH_SHORT).show()
        }

        //Get intent FilterFoodActivity
        modelFilter = intent.getSerializableExtra("detailRecipe") as ModelFilter
        if (modelFilter != null) {
            idMeal = modelFilter!!.idMeal
            strMeal = modelFilter!!.strMeal

            //Set text
            binding.tvTitle.text = strMeal
            binding.tvTitle.isSelected = true

            //Get image source
            Glide.with(this)
                .load(modelFilter!!.strMealThumb)
                .placeholder(R.drawable.ic_food_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgThumb)

            //Method get data
            detailRecipe
        }
    }

    private val detailRecipe: Unit
        private get() {
            Log.d("DetailRecipeActivity", "idMeal: $idMeal")
            progressDialog!!.show()
            AndroidNetworking.get(Api.DetailRecipe)
                .addPathParameter("idMeal", idMeal)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            progressDialog!!.dismiss()
                            val playerArray = response.getJSONArray("meals")
                            for (i in 0 until playerArray.length()) {
                                val temp = playerArray.getJSONObject(i)
                                val dataApi = ModelDetailRecipe()
                                val Instructions = temp.getString("strInstructions")
                                binding.tvInstructions!!.text = Instructions

                                val Category = temp.getString("strCategory")
                                val Area = temp.getString("strArea")
                                binding.tvSubTitle!!.text = "$Category | $Area"

                                val Source = temp.getString("strSource")
                                binding.tvSource!!.setOnClickListener { v: View? ->
                                    val intentYoutube = Intent(Intent.ACTION_VIEW)
                                    intentYoutube.data = Uri.parse(Source)
                                    startActivity(intentYoutube)
                                }

                                val Youtube = temp.getString("strYoutube")
                                binding.tvYoutube!!.setOnClickListener { v: View? ->
                                    val intentYoutube = Intent(Intent.ACTION_VIEW)
                                    intentYoutube.data = Uri.parse(Youtube)
                                    startActivity(intentYoutube)
                                }
                                val StrArea = temp.getString("strArea")
                                binding.tvShareRecipe!!.setOnClickListener {
                                    val intent = Intent(this@DetailRecipeActivity, LocationRecipeActivity::class.java)
                                    intent.putExtra("strArea", StrArea)
                                    startActivity(intent)

                                }

                                for (n in 1 .. 20){
                                    val ingredient = temp.getString("strIngredient$n")
                                    val measure = temp.getString("strMeasure$n")
                                    if (ingredient.trim() != "" && ingredient.trim() != "null") binding.tvIngredients.append("\n \u2022 $ingredient")
                                    if (measure.trim() != "" && measure.trim() != "null") binding.tvMeasure.append("\n : $measure")
                                }

                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(this@DetailRecipeActivity,
                                "Failed to display data!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        progressDialog!!.dismiss()
                        Toast.makeText(this@DetailRecipeActivity,"No internet connection!", Toast.LENGTH_SHORT).show()
                    }
                })
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