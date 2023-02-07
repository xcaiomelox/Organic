package dominio.proprio.organic.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import dominio.proprio.organic.R
import dominio.proprio.organic.adapter.AppDataBase
import dominio.proprio.organic.database.dao.FoodDao
import dominio.proprio.organic.databinding.FragmentRegisterBinding
import dominio.proprio.organic.model.Food

class RegisterFragment : Fragment() {

        private lateinit var binding: FragmentRegisterBinding
    private val args: RegisterFragmentArgs by navArgs()
    private val food: Food? by lazy { args.fooddata }
    private val dao by lazy {
        AppDataBase.instance(context).foodDao()
    }
    private var foodId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        setButtonBackToHome()
        setButtonSave()
        fillFields()
        return binding.root
    }

    private fun setButtonSave() {
        binding.buttonSave.setOnClickListener {
            val food = getFoodValue()
            dao.save(food)
            activity?.onBackPressed()
        }
    }

    private fun setButtonBackToHome() {
        binding.buttonBackToHome.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun getFoodValue(): Food = with(binding) {
        Food(
            id = food?.id ?: foodId,
            title = editTextTitle.text.toString(),
            description = editTextDescription.text.toString(),
            price = editTextPrice.getDouble(),
            image = editTextImageUrl.text.toString()
        )
    }

    fun EditText.getDouble(): Double = this.text.toString().toDouble()

    private fun fillFields() {
        with(binding){
            food?.let {
                editTextTitle.setText(it.title)
                editTextDescription.setText(it.description)
                editTextPrice.setText(it.price.toString())
                editTextImageUrl.setText(it.image)
                Glide.with(foodImage).load(food!!.image).into(foodImage)
            }
        }
    }
}

