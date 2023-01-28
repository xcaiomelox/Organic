package dominio.proprio.organic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dominio.proprio.organic.databinding.FoodBinding
import dominio.proprio.organic.model.Food

class Adapter(
    foodsList:List<Food>
): RecyclerView.Adapter<Adapter.FoodViewHolder>() {

    private val foodsList = foodsList.toMutableList()

    class FoodViewHolder(val binding: FoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = FoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentFood = foodsList[position]
        with(holder.binding){
            Glide.with(foodImageView).load(currentFood.image).into(foodImageView)
            textViewTitle.text = currentFood.title
            textViewDescription.text = currentFood.description
            textViewPrice.text = currentFood.price.toString()
        }
    }

    override fun getItemCount() = foodsList.size

}