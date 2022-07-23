package com.e444er.exoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import com.e444er.exoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.dash.manifest.UrlTemplate
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), Player.Listener{

    lateinit var binding: ActivityMainBinding
    lateinit var player: Player
    lateinit var playerView: PlayerView
    lateinit var text1: TextView
    lateinit var prog: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prog = findViewById(R.id.progressBar)
        text1 = findViewById(R.id.title1)

        initPlayer()
        addMp3Files()
        addMp4Files()
    }

    private fun initPlayer(){
        player  = ExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.videoView)
        playerView.player = player
        player.addListener(this)
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
    }


    private fun addMp4Files(){
        val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4))
        player.addMediaItem(mediaItem)
        player.prepare()
    }


    private fun addMp3Files(){
        val mediaItem = MediaItem.fromUri(getString(R.string.test_mp3))
        player.addMediaItem(mediaItem)
        player.prepare()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when(playbackState){
            Player.STATE_BUFFERING -> {
                prog.isVisible = false
            }
            Player.STATE_READY -> {
                prog.isVisible = true
            }
        }
    }

    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
        super.onMediaMetadataChanged(mediaMetadata)
        text1.text = mediaMetadata.title ?: mediaMetadata.displayTitle ?: " no title"
    }




}
