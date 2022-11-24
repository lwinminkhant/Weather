package com.lmkhant.weather

import android.content.Intent
import android.os.Build.VERSION_CODES.S
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.TOUCH_SLOP_DEFAULT
import com.lmkhant.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val forecastRepository = ForecastRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener {
            val zipcode = binding.zipcodeEditText.text.toString()
            if (zipcode.length != 5) {
                Toast.makeText(this, R.string.zipcode_entry_error, Toast.LENGTH_SHORT).show()
            } else {
                forecastRepository.loadForecast(zipcode)
            }
        }
        val forecastList: RecyclerView = binding.forecastList
        forecastList.layoutManager = LinearLayoutManager(this)

        val dailyForecastAdaptor = DailyForecastAdaptor { forecast->
            showForecastDetails(forecast)
        }
        forecastList.adapter = dailyForecastAdaptor

        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            dailyForecastAdaptor.submitList(forecastItems)
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }
    private fun showForecastDetails(forecast: DailyForecast){
        val forecastDetailIntent = Intent(this, ForecastDetailActivity::class.java)
        forecastDetailIntent.putExtra("key_temp", forecast.temp)
        forecastDetailIntent.putExtra("key_description", forecast.description)
        startActivity(forecastDetailIntent)
    }
}