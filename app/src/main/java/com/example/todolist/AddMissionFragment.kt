package com.example.todolist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolist.data.AppDatabase
import com.example.todolist.repository.MissionItemRepository
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
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val missionItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
        val missionItemRepo = MissionItemRepository(missionItemDb)
        val viewModelFactory = AnyViewModelFactory {
            MissionViewModel(missionItemRepo)
        }

        val missionViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(MissionViewModel::class.java)

        add_btn.setOnClickListener {
            if (new_mission.text.isNullOrEmpty()) {
                new_mission.error = "請輸入待辦事項"
            } else {
                new_mission.error = null

                view.clearFocus()
                missionViewModel.createNewMission(new_mission.text.toString())
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                findNavController().popBackStack()
            }
        }
    }
}