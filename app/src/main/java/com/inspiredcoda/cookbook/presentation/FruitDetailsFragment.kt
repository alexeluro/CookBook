package com.inspiredcoda.cookbook.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.inspiredcoda.cookbook.data.response.FruitData
import com.inspiredcoda.cookbook.databinding.FragmentFruitDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FruitDetailsFragment : Fragment() {

    private var _binding: FragmentFruitDetailsBinding? = null
    private val binding: FragmentFruitDetailsBinding
        get() = _binding!!

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFruitDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fruit = arguments?.get("fruit") as FruitData

        binding.apply {
            val id =
                context?.resources?.getIdentifier(fruit.image, "drawable", context?.packageName)
            val drawable = context?.resources?.getDrawable(id!!, context?.theme)
            Glide.with(requireContext())
                .load(drawable)
                .into(binding.fruitPic)
            binding.toolBar.apply {
                title.text = fruit.productName
                backBtn.setOnClickListener { navController.popBackStack() }
            }
            binding.descContent.text = fruit.description
            binding.locationContent.text = fruit.from
            binding.nutrientContent.text = fruit.nutrients
            binding.organicContent.text = if (fruit.organic!!) "YES" else "NO"
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}