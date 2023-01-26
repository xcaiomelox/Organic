package dominio.proprio.organic.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dominio.proprio.organic.R
import dominio.proprio.organic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}