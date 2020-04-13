package nl.giorgos.mylibrary

import android.content.Context
import org.webrtc.DefaultVideoDecoderFactory
import org.webrtc.DefaultVideoEncoderFactory
import org.webrtc.EglBase
import org.webrtc.PeerConnectionFactory

class Connection(val applicationContext: Context) {
    private val rootEglBase by lazy { EglBase.create() }

    private val peerConnectionFactory: PeerConnectionFactory by lazy {
        //Initialize PeerConnectionFactory globals.
        val initializationOptions = PeerConnectionFactory.InitializationOptions.builder(applicationContext)
                .createInitializationOptions()

        PeerConnectionFactory.initialize(initializationOptions)

        //Create a new PeerConnectionFactory instance - using Hardware encoder and decoder.
        val options = PeerConnectionFactory.Options()

        val defaultVideoEncoderFactory = DefaultVideoEncoderFactory(
                rootEglBase.eglBaseContext, /* enableIntelVp8Encoder */true, /* enableH264HighProfile */true)
        val defaultVideoDecoderFactory = DefaultVideoDecoderFactory(rootEglBase.eglBaseContext)
        println("GIO pare na xeis")
//        PeerConnectionFactory(options, defaultVideoEncoderFactory, defaultVideoDecoderFactory)
        PeerConnectionFactory.builder()
                .setOptions(options)
                .setVideoEncoderFactory(defaultVideoEncoderFactory)
                .setVideoDecoderFactory(defaultVideoDecoderFactory)
                .createPeerConnectionFactory()

    }

    fun testFactory() {
        peerConnectionFactory.createLocalMediaStream("test")
        peerConnectionFactory.dispose()
        println("GIO pare na xeis2")
    }
}