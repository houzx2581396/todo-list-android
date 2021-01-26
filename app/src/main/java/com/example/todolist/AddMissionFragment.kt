package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.add_mission_fragment.*

class AddMissionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_mission_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val missionViewModel =
            ViewModelProvider(requireActivity()).get(MissionViewModel::class.java)

        add_btn.setOnClickListener {
            missionViewModel.onNewTodo.postValue(new_mission.text.toString())
            findNavController().popBackStack()
        }
    }
}