package xyz.vivekc.webrtccodelab


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession


class Utils {
    private var retrofitInstance: Retrofit? = null
    fun getRetrofitInstance(): TurnServer? {
        if (retrofitInstance == null) {
            val loggingInterceptor = HttpLoggingInterceptor()
            val httpClient = OkHttpClient.Builder().apply {
                addInterceptor(BasicAuthInterceptor(Constants.USERNAME, Constants.PASSWORD))
                addInterceptor(loggingInterceptor)
                hostnameVerifier(HostnameVerifier { hostname, _ ->
                    "global.xirsys.net" == hostname
                })
            }.build()
            retrofitInstance = Retrofit.Builder()
                    .baseUrl(API_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
        }
        return retrofitInstance?.create(TurnServer::class.java)
    }

    companion object {
        private var instance: Utils? = null
        //global webrtc cloud server
        val API_ENDPOINT: String = "https://global.xirsys.net"
        fun getInstance(): Utils? {
            if (instance == null) {
                instance = Utils()
            }
            return instance
        }
    }
}