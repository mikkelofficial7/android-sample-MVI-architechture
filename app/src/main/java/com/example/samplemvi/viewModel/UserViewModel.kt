package com.example.samplemvi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplemvi.intent.UserIntent
import com.example.samplemvi.model.IModel.IModel
import com.example.samplemvi.network.NetworkRequest
import com.example.samplemvi.state.UserState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(), IModel<UserState, UserIntent> {

    override val intents: Channel<UserIntent> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<UserState>().apply { value = UserState() }
    override val states: LiveData<UserState> get() = _state

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { userIntent ->
                when(userIntent) {
                    UserIntent.RefreshUsers -> fetchData()
                    UserIntent.FetchUsers -> fetchData()
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(isLoading = true) }
                updateState { it.copy(isLoading = false, entries = NetworkRequest.apiService.getEntries()) }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: UserState) -> UserState) {
        _state.postValue(handler(states.value!!))
    }
}