package dominio.proprio.organic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dominio.proprio.organic.R
import dominio.proprio.organic.adapter.Adapter
import dominio.proprio.organic.adapter.AppDataBase
import dominio.proprio.organic.database.dao.FoodDao
import dominio.proprio.organic.databinding.FragmentHomeBinding
import dominio.proprio.organic.model.Food


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val dao by lazy {
        AppDataBase.instance(context).foodDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        setRecyclerVIew(dao.getAll())
        setFloattingActionButton()
        return binding.root
    }

    private fun setRecyclerVIew(foodsList: List<Food>) {

        val recyclerView = binding.homeRecyclerView
        var foodAdapter = Adapter(
            foodsList = foodsList,
            onItemCLicked = {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            }
            )
        recyclerView.adapter = foodAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setFloattingActionButton() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
        }
    }
}