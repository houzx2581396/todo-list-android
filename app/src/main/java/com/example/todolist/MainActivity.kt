package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val missionAdapter = MissionAdapter()
        todo_list_view.adapter = missionAdapter
        todo_list_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val missionViewModel = ViewModelProvider(this).get<MissionViewModel>()

        missionViewModel.missions.observe(this, Observer { mission ->
            missionAdapter.submitList(mission)
        })

        add_mission_btn.setOnClickListener {
            missionViewModel.addNewTodo("gogo")
        }
    }
}