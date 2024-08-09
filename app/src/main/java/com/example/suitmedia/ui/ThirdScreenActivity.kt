package com.example.suitmedia.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmedia.data.response.UsersItem
import com.example.suitmedia.databinding.ActivityThirdScreenBinding

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val thirdScreenViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ThirdScreenViewModel::class.java)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpAction()

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        thirdScreenViewModel.listUser.observe(this){ userItem ->
            setUserData(userItem)
        }
        thirdScreenViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setUpAction(){

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun setUserData(useritem: List<UsersItem>) {
        val adapter = UserAdapter(this)
        adapter.submitList(useritem)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}