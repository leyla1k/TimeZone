package com.example.timezone.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timezone.App
import com.example.timezone.R
import com.example.timezone.data.Result
import com.example.timezone.databinding.FragmentResultsBinding
import com.example.timezone.ui.rv.ResultItemTouchHelperCallback
import com.example.timezone.ui.rv.ResultRVAdapter
import com.example.timezone.ui.rv.VerticalSpaceItemDecoration
import com.example.timezone.ui.viewmodels.ResultViewModel
import com.example.timezone.ui.viewmodels.ResultViewModelFactory
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID


class ResultsFragment : Fragment() {

    private val resultAdapter = ResultRVAdapter()
    private val callback: ResultItemTouchHelperCallback =
        ResultItemTouchHelperCallback(resultAdapter)
    private val touchHelper = ItemTouchHelper(callback)

    val viewModel: ResultViewModel by viewModels { ResultViewModelFactory((requireActivity().application as App).resultRepository) }


    private val victory by lazy { navArgs<ResultsFragmentArgs>().value.victory }
    private val time by lazy { navArgs<ResultsFragmentArgs>().value.time }
    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResultsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (victory) {

            binding.animationView.playAnimation()
            binding.tvResult.text = "УСПЕХ!"
            binding.tvResult.setTextColor(ContextCompat.getColor(requireActivity(), R.color.green))

        } else {
            binding.animationView.setAnimation(R.raw.animation_check_false)
            binding.animationView.playAnimation()
            binding.tvResult.text = "ОШИБКА!"
            binding.tvResult.setTextColor(ContextCompat.getColor(requireActivity(), R.color.red))
        }
        with(binding.rvResults) {
            touchHelper.attachToRecyclerView(this)
            adapter = resultAdapter
            layoutManager = LinearLayoutManager(requireContext())
                .apply {
                    addItemDecoration(
                        VerticalSpaceItemDecoration(50)
                    )
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.resultListFlow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED
            ).collect {
                resultAdapter.submit(it, binding.rvResults)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.insertNewResult(
                Result(
                    UUID.randomUUID(),
                    time,
                    Date(),
                    victory
                )
            )
        }

        binding.btPlayAgain.setOnClickListener {
            findNavController().navigate(
                ResultsFragmentDirections.actionResultsFragmentToModeFragment()
            )
        }
        super.onViewCreated(view, savedInstanceState)
    }


}