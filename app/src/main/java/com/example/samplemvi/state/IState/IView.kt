package com.example.samplemvi.state.IState

interface IView<S: IState> {
    fun render(state: S)
}