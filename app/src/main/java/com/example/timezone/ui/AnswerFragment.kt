package com.example.timezone.ui


import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.timezone.Game
import com.example.timezone.R

import com.example.timezone.databinding.FragmentAnswerBinding
import java.text.DecimalFormat
import java.text.NumberFormat


class AnswerFragment : Fragment() {

    private var _binding: FragmentAnswerBinding? = null
    private val binding get() = _binding!!
    private val time by lazy { navArgs<AnswerFragmentArgs>().value.time }
    private val city by lazy { navArgs<AnswerFragmentArgs>().value.city }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val f: NumberFormat = DecimalFormat("0")
                val sec = millisUntilFinished / 1000 % 60
                binding.tvHugeTimer.text = f.format(sec)
            }

            override fun onFinish() {
                binding.tvHugeTimer.text = ""
                questionCreating()
                binding.buttonOk.setOnClickListener {
                    if (binding.edAnswer.text.isNotEmpty()) {
                        val victory= binding.edAnswer.text.toString()==city
                        findNavController().navigate(
                            AnswerFragmentDirections.actionAnswerFragmentToResultsFragment(time,victory)
                        )
                    }
                }
            }
        }.start()

        super.onViewCreated(view, savedInstanceState)
    }


    fun questionCreating() {

        val ivQuestion = ImageView(requireActivity())
        ivQuestion.setBackgroundResource(R.drawable.time_eight)

        val layoutParams = ConstraintLayout.LayoutParams(
            resources.getDimension(R.dimen.imageview_question_width).toInt(), // width
            resources.getDimension(R.dimen.imageview_question_height).toInt()
        )
        layoutParams.leftToLeft = ConstraintSet.PARENT_ID
        layoutParams.bottomToBottom = ConstraintSet.PARENT_ID
        layoutParams.rightToRight = ConstraintSet.PARENT_ID
        layoutParams.topToTop = ConstraintSet.PARENT_ID
        layoutParams.setMargins(0, 0, 0, 0)
        ivQuestion.layoutParams = layoutParams

        binding.constraintlayout.addView(ivQuestion)

    }

}