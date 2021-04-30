package com.sulistyo.unittesting

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sulistyo.unittesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bind: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        mainViewModel = MainViewModel(cuboidModel = CuboidModel())

        bind.btnSave.setOnClickListener { applicationContext }
        bind.btnCalculateCircumference.setOnClickListener { applicationContext }
        bind.btnCalculateSurfaceArea.setOnClickListener { applicationContext }
        bind.btnCalculateVolume.setOnClickListener { applicationContext }

    }

    override fun onClick(v: View) {
        val length = bind.etLength.text.toString().trim()
        val width = bind.etWidth.text.toString().trim()
        val height = bind.etHeigh.text.toString().trim()

        when {
            TextUtils.isEmpty(length) -> {
                bind.etLength.error = "Field tidak boleh kosong"
            }
            TextUtils.isEmpty(width) -> {
                bind.etWidth.error = "Field tidak boleh kosong"
            }
            TextUtils.isEmpty(height) -> {
                bind.etHeigh.error = "Field tidak boleh kosong"
            }

            else -> {
                val valueLength = length.toDouble()
                val valueHeigh = height.toDouble()
                val valueWidth = width.toDouble()

                when (v.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(valueLength, valueWidth, valueHeigh)
                        visible()
                    }
                    R.id.btn_calculate_circumference -> {
                        bind.tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        bind.tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btn_calculate_volume -> {
                        bind.tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        bind.btnCalculateVolume.visibility = View.VISIBLE
        bind.btnCalculateCircumference.visibility = View.VISIBLE
        bind.btnCalculateSurfaceArea.visibility = View.VISIBLE
        bind.btnSave.visibility = View.GONE
    }

    private fun gone() {
        bind.btnCalculateVolume.visibility = View.GONE
        bind.btnCalculateCircumference.visibility = View.GONE
        bind.btnCalculateSurfaceArea.visibility = View.GONE
        bind.btnSave.visibility = View.VISIBLE
    }
}