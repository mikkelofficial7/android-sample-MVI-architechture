package com.example.samplemvi.state
import com.example.samplemvi.model.Entries
import com.example.samplemvi.state.IState.IState

data class UserState(
    val entries: Entries? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) : IState