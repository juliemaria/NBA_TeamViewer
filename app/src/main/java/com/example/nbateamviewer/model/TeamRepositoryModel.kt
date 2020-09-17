package com.example.nbateamviewer.model

import java.util.*

class TeamRepositoryModel {
    var teamsArrayList: ArrayList<Teams>? = null
        private set
    var throwable: Throwable? = null
        private set
    var errorMessage: String? = null
        private set

    constructor(body: ArrayList<Teams>?) {
        teamsArrayList = body
    }

    constructor(body: String?) {
        errorMessage = body
    }

    constructor(throwable: Throwable?) {
        this.throwable = throwable
    }

}