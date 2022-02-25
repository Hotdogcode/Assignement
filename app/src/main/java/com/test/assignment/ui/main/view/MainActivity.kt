package com.test.assignment.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.assignment.R
import com.test.assignment.data.model.TrendingRepo
import com.test.assignment.databinding.ActivityMainBinding
import com.test.assignment.ui.main.adapter.MainAdapter
import com.test.assignment.ui.main.viewmodel.MainViewModel
import com.test.assignment.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    lateinit var binding: ActivityMainBinding
    private var items = arrayListOf<TrendingRepo>()
    private var matchedRepo: ArrayList<TrendingRepo> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter()
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
                        items.clear()
                        items.addAll(repos)
                        renderList(items)
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

    private fun renderList(repos:ArrayList<TrendingRepo>) {
        adapter.setItems(repos)
        binding.searchView.isSubmitButtonEnabled = true
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
        matchedRepo = arrayListOf()

        text?.let {
            items.forEach { repo ->
                if (repo.name.common.contains(text, true)) {
                    matchedRepo.add(repo)
                }
            }
            renderList(matchedRepo)
            if (matchedRepo.isEmpty()) {
                Toast.makeText(this, "No match found!", Toast.LENGTH_SHORT).show()
            }
            renderList(matchedRepo)
        }
    }



}