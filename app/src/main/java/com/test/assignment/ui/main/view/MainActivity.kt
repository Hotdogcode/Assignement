package com.test.assignment.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.databinding.ActivityMainBinding
import com.test.assignment.ui.main.adapter.MainAdapter
import com.test.assignment.ui.main.viewmodel.MainViewModel
import com.test.assignment.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }



    private fun setupUI() {
        adapter = MainAdapter(mainViewModel.listData,onItemSelected)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }
    private fun setupObserver() {
        mainViewModel.repos.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    it.data?.let { repos ->
                        if(repos.isNotEmpty()) {
                            Log.e("aslam",repos[0].flag)
                            mainViewModel.updateData(repos)
                            renderList()
                        }
                        performSearch()
                    }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private val onItemSelected = { repo: TrendingRepo, position: Int ->
        mainViewModel.curSelectedIndex = position
        if(mainViewModel.curSelectedIndex==mainViewModel.preSelectedIndex){
            //deselect
            adapter.updateSelection(mainViewModel.curSelectedIndex,false)
            mainViewModel.curSelectedIndex = -1
            mainViewModel.preSelectedIndex = -1

        }else{
            //select
            adapter.updateSelection(mainViewModel.preSelectedIndex,false)
            adapter.updateSelection(mainViewModel.curSelectedIndex,true)
            mainViewModel.preSelectedIndex = mainViewModel.curSelectedIndex

        }

    }

    private fun renderList() {
        adapter.refresh()
        binding.searchView.isSubmitButtonEnabled = true
        if(mainViewModel.curSelectedIndex!=-1 || mainViewModel.preSelectedIndex!=-1){
            adapter.updateSelection(mainViewModel.curSelectedIndex,false)
            adapter.updateSelection(mainViewModel.preSelectedIndex,false)
        }
    }


    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }

    private fun search(text: String?) {
        val matchedItems = ArrayList<TrendingRepo>()
        text?.let {
            mainViewModel.items.forEach { repo ->
                if (repo.name.common.contains(text, true)) {
                    matchedItems.add(repo)
                }
            }
            mainViewModel.updateMatchedData(matchedItems)
            if (matchedItems.isEmpty()) {
                Toast.makeText(this, "No match found!", Toast.LENGTH_SHORT).show()
            }
            renderList()
        }
    }



}