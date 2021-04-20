package com.karthick.mvvm.data.repository


enum class Status {
    RUNNING,
    SUCCESS,
    FAIL
}

class NetworkState(val status: Status, val msg: String) {

    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val ENDOFLIST: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            
            LOADING = NetworkState(Status.RUNNING, "Running")

            ERROR = NetworkState(Status.FAIL, "Something went wrong")

            ENDOFLIST  = NetworkState(Status.FAIL, "You have reached the end")
        }
    }


}