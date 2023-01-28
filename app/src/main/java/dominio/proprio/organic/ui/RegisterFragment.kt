package dominio.proprio.organic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import dominio.proprio.organic.R
import dominio.proprio.organic.adapter.FoodDao
import dominio.proprio.organic.databinding.FragmentRegisterBinding
import dominio.proprio.organic.model.Food

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        setButtonBackToHome()
        setButtonSave()
        return binding.root
    }

    private fun setButtonSave() {
        binding.buttonSave.setOnClickListener {
            FoodDao.foods.add(getFoodValue())
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }

    private fun setButtonBackToHome() {
        binding.buttonBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }

    private fun getFoodValue(): Food = with(binding) {
        Food(
            title = editTextTitle.text.toString(),
            description = editTextDescription.text.toString(),
            price = editTextPrice.getDouble(),
            image = editTextImage.text.toString()
        )
    }

    fun EditText.getDouble(): Double = this.text.toString().toDouble()
}