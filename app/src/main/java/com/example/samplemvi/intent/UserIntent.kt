package com.example.samplemvi.intent

sealed class UserIntent : IIntent {
    object RefreshUsers : UserIntent()
    object FetchUsers : UserIntent()
}