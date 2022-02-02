package com.example.samplemvi.model.IModel

import androidx.lifecycle.LiveData
import com.example.samplemvi.intent.IIntent
import com.example.samplemvi.state.IState.IState
import kotlinx.coroutines.channels.Channel

interface IModel<S: IState, I: IIntent> {
    val intents: Channel<I>
    val states: LiveData<S>
}