package com.example.nbateamviewer.network.model

data class Teams(
        val wins : Int?,
        val losses: Int?,
        val full_name: String?,
        val id: Int?,
        val players: List<Players>
)