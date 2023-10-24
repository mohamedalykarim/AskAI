package mohalim.ai.askai.core.hilt

import android.content.Context
import android.webkit.WebView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object Modules {

    @Provides
    fun provideWebView(@ApplicationContext context: Context): WebView {
        val webView = WebView(context)
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = true;
        webView.settings.loadWithOverviewMode = true;
        webView.settings.domStorageEnabled = true


        return webView

    }
}