package com.example.samplemvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.samplemvi.intent.UserIntent
import com.example.samplemvi.state.IState.IView
import com.example.samplemvi.state.UserState
import com.example.samplemvi.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), IView<UserState> {
    private val mViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observing the state
        mViewModel.states.observe(this, Observer {
            render(it)
        })

        // Fetching data when the application launched
        lifecycleScope.launch {
            mViewModel.intents.send(UserIntent.FetchUsers)
        }
    }

    override fun render(state: UserState) {
        var dataString = ""

        with(state) {
            pb_load.isVisible = isLoading

            entries?.listEntries?.forEachIndexed { index, itemEntries ->
                if(index > 10) return@forEachIndexed
                dataString += index.toString()+". "+itemEntries.api+" ("+itemEntries.link+")\n"
            }

            tv_hello.text = dataString

            if (errorMessage != null) {
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}