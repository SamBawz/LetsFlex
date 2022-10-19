package com.example.letsflex.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.letsflex.databinding.FragmentMeBinding

class MeFragment : Fragment() {

    private var _binding: FragmentMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val meViewModel =
            ViewModelProvider(this).get(MeViewModel::class.java)

        _binding = FragmentMeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //Gets the string from the view model and displays it in the textView
        //This technique is used to prevent fetching data again when navigating between activities
        /*
        val textView: TextView = binding.textDashboard
        meViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
         */


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}