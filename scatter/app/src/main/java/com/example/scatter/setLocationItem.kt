package com.example.scatter

import android.content.ClipData.Item
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.scatter.databinding.MainActivityBinding
class setLocationItem : AppCompatActivity() {
    private lateinit var inflater : MenuInflater

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_navigation, menu)
        return true
    }

    override fun onOptionsItemSeleted(item: MenuItem): Boolean{
        when (item.itemId){
            android.R.id.home ->{
                finish()
            }
            R.id.login -> {
                val intent = Intent(this, CongetionPrediction::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}