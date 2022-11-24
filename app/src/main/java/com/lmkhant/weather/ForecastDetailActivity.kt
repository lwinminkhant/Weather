package com.lmkhant.weather

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.lmkhant.weather.databinding.ActivityForecastDetailBinding

class ForecastDetailActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityForecastDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForecastDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        "${intent.getFloatExtra("key_temp",0f)}°".also { binding.tempText.text = it }
        binding.textView.text = intent.getStringExtra("key_description")

        //setSupportActionBar(binding.toolbar)
        //setTitle(R.string.title_activity_forecast_detail)
        /*val navController = findNavController(R.id.nav_host_fragment_content_forecast_detail)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }
}