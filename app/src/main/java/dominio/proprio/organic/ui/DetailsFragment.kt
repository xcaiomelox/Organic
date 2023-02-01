package dominio.proprio.organic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dominio.proprio.organic.R
import dominio.proprio.organic.adapter.AppDataBase
import dominio.proprio.organic.databinding.FragmentDetailsBinding
import dominio.proprio.organic.model.Food

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val food: Food by lazy { args.fooddata }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding = FragmentDetailsBinding.inflate(inflater)
        fillFields()
        setupGoBackButton()
        setImageButtonDelete()
        setImageButtonEdit()
        return binding.root
    }

    private fun fillFields(){
        with(binding){
            Glide.with(imageViewDetailProduct).load(food.image).into(imageViewDetailProduct)
            textViewTitleDetailProduct.text = food.title
            textviewDescriptionDetailProduct.text = food.description
            textviewPriceDetailProduct.text = food.price.toString()
        }
    }

    private fun setupGoBackButton(){
        binding.buttonGoHome.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setImageButtonDelete(){
        binding.imageButtonDelete.setOnClickListener {
            val db = AppDataBase.instance(context)
            food.let { it1 -> db.foodDao().delete(it1) }
            activity?.onBackPressed()
        }
    }

    private fun setImageButtonEdit(){
        binding.imageButtonEdit.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToRegisterFragment(food)
            findNavController().navigate(action)
        }
    }
}