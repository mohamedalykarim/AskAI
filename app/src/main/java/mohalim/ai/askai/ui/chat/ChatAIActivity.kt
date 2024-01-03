package mohalim.ai.askai.ui.chat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import mohalim.ai.askai.R
import javax.inject.Inject

@AndroidEntryPoint
class ChatAIActivity : AppCompatActivity() {
    @Inject
    lateinit var webView: WebView;

    private var mInterstitialAd: InterstitialAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra("URL")
        if (url != null) {
            webView.loadUrl(url)
        } else {
            finish()
        }

        setContent {
            ChatAIActivityUI(webView = webView, context = this@ChatAIActivity)
        }

        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            "ca-app-pub-5350581213670869/3777365388",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    adError?.toString()?.let { Log.d("TAG", it) }
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("TAG", "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })

    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }

    }
}


@Composable
fun ChatAIActivityUI(
    webView: WebView,
    context: Context,
) {
    var backEnabled by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(true) }

    webView.webViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            loading = true
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            loading = true
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            loading = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                webView.apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }


            }, update = {

            })

        if (loading) {
            Column {
                val imageLoader = ImageLoader.Builder(context)
                    .components {
                        if (SDK_INT >= 28) {
                            add(ImageDecoderDecoder.Factory())
                        } else {
                            add(GifDecoder.Factory())
                        }
                    }.build()

                Image(
                    painter = rememberAsyncImagePainter(R.drawable.loading, imageLoader),
                    contentDescription = "Loading",
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                )
            }
        }


    }




    BackHandler(enabled = backEnabled) {
        webView.goBack()
    }
}

