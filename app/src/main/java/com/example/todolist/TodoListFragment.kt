package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.data.AppDatabase
import com.example.todolist.repository.MissionItemRepository
import kotlinx.android.synthetic.main.todo_list_fragment.*

class TodoListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todo_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val missionAdapter = MissionAdapter()
        todo_list_view.adapter = missionAdapter
        todo_list_view.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val missionItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
        val missionItemRepo = MissionItemRepository(missionItemDb)
        val viewModelFactory = AnyViewModelFactory {
            MissionViewModel(missionItemRepo)
        }

        val missionViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(MissionViewModel::class.java)
        missionViewModel.missionLiveData.observe(
            viewLifecycleOwner,
            Observer { mission: List<Mission> ->
                missionAdapter.submitList(mission)
            })

        add_mission_btn.setOnClickListener {
            findNavController().navigate(TodoListFragmentDirections.actionTodoListFragmentToAddMissionFragment())
        }
    }
}