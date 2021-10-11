package com.inspiredcoda.cookbook.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.inspiredcoda.cookbook.databinding.FragmentFruitBinding
import com.inspiredcoda.cookbook.presentation.adapter.FruitAdapter
import com.inspiredcoda.cookbook.presentation.viewmodel.MainViewModel
import com.inspiredcoda.cookbook.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FruitFragment : Fragment() {

    private var _binding: FragmentFruitBinding? = null
    private val binding: FragmentFruitBinding
        get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }
    private lateinit var mAdapter: FruitAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFruitBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBar.backBtn.apply {
            show(false)
            setOnClickListener { navController.popBackStack() }
        }
        mAdapter = FruitAdapter { fruitData ->
            navController.navigate(
                FruitFragmentDirections.actionFruitFragmentToFruitDetailsFragment(
                    fruitData
                )
            )
        }

        observer()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                } else {
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
                }
        }
    }

    private fun observer() {
        mainViewModel.loadingState.observe(viewLifecycleOwner) { state ->
            binding.progressBar.show(state)
        }

        mainViewModel.fruitDatas.observe(viewLifecycleOwner) { fruitDatas ->
            if (fruitDatas.isNotEmpty()) {
                mAdapter.submitFruits(fruitDatas)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}