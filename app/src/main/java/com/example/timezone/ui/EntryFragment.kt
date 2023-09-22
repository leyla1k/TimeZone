package com.example.timezone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.timezone.R
import com.example.timezone.databinding.FragmentEntryBinding


class EntryFragment : Fragment() {

    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonPlay.setOnClickListener {
            findNavController().navigate(
                EntryFragmentDirections.actionEntryFragmentToModeFragment()
            )
        }

        super.onViewCreated(view, savedInstanceState)
    }

}