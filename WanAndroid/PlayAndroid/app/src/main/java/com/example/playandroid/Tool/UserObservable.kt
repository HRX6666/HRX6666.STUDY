package com.example.playandroid.Tool

import androidx.databinding.ObservableField

class UserObservable {
    val name: ObservableField<String> by lazy {
        ObservableField<String>()
    }

}