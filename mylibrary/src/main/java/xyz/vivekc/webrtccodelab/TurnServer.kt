package xyz.vivekc.webrtccodelab

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.PUT


interface TurnServer {
    //https://global.xirsys.net/_turn/Pouli?format=urls
    @PUT("/_turn/Pouli?format=urls")
    fun getIceCandidates(): Call<TurnServerModel?>?
}