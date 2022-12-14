package com.example.letsflex.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.letsflex.ExerciseDetailsActivity
import com.example.letsflex.MainActivity
import com.example.letsflex.NewExercisesActivity
import com.example.letsflex.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.btnExerciseChildPose.setOnClickListener() {
            val intent = Intent(activity, ExerciseDetailsActivity::class.java)
            startActivity(intent)
        }

        binding.btnAddExercise.setOnClickListener() {
            val intent = Intent(activity, NewExercisesActivity::class.java)
            startActivity(intent)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}