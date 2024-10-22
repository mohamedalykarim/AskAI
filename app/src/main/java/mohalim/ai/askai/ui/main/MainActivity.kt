package mohalim.ai.askai.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.Color.parseColor
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import mohalim.ai.askai.R
import mohalim.ai.askai.ui.chat.ChatAIActivity


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityUI(this@MainActivity)
        }

        MobileAds.initialize(this) { }

    }
}

@Composable
fun MainActivityUI(context: Activity) {
    val scrollState = rememberScrollState()

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms.fonts",
        certificates = 0
    )
    val fontName = GoogleFont("Roboto")

    val fontFamily = FontFamily(
        Font(googleFont = fontName, fontProvider = provider)
    )


    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .paint(
                painterResource(id = R.drawable.transparent_bg),
                contentScale = ContentScale.FillBounds
            )
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {


        Row() {
            /** Gemini **/
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(5.dp)
                    .weight(1f)
                    .background(
                        color = Color(parseColor("#E7CCCC")),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .clickable {
                        val intent = Intent(context, ChatAIActivity::class.java)
                        intent.putExtra("URL", "https://gemini.google.com/")
                        context.startActivity(intent)
                    }
            ) {
                Image(
                    painterResource(id = R.drawable.bard),
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp),
                    contentDescription = "Google Bard"
                )
                Text(
                    text = "Gemini",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(parseColor("#153448")),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "أسأل الذكاء الاصطناعي",
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center
                )
            }

            /** Chat GPT **/

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(5.dp)
                    .weight(1f)
                    .background(
                        color = Color(parseColor("#153448")),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(16.dp, 0.dp)
                    .clickable {
                        val intent = Intent(context, ChatAIActivity::class.java)
                        intent.putExtra("URL", "https://chatgpt.com/")
                        context.startActivity(intent)
                    }
            ) {


                Image(
                    painterResource(id = R.drawable.chat_gpt),
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp),
                    contentDescription = "ChatGPT"
                )
                Text(
                    text = "ChatGPT",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(parseColor("#ffffff")),
                    textAlign = TextAlign.Center

                )
                Text(
                    text = "أسأل الذكاء الاصطناعي",
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center
                )
            }


            /** AI CHATTING **/
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(5.dp)
                    .weight(1f)
                    .background(
                        color = Color(parseColor("#153448")),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(16.dp, 0.dp)
                    .clickable {
                        val intent = Intent(context, ChatAIActivity::class.java)
                        intent.putExtra("URL", "https://www.aichatting.net/")
                        context.startActivity(intent)
                    }
            ) {


                Image(
                    painterResource(id = R.drawable.ai_chatting),
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp),
                    contentDescription = "AI Chatting"
                )
                Text(
                    text = "AIChatting",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(parseColor("#ffffff")),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "أسأل الذكاء الاصطناعي",
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        /** Adobe Firefly **/
        /** https://www.adobe.com/sensei/generative-ai/firefly.html */

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .weight(1f)
                .background(
                    color = Color(parseColor("#009796")),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp, 0.dp)
                .clickable {
                    val intent = Intent(context, ChatAIActivity::class.java)
                    intent.putExtra(
                        "URL",
                        "https://www.adobe.com/sensei/generative-ai/firefly.html"
                    )
                    context.startActivity(intent)
                }
        ) {

            Image(
                painterResource(id = R.drawable.firefly),
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                contentDescription = "Adobe Firefly"
            )
            Text(
                text = "Adobe Firefly",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(text = "اصنع صور من مخيلتك", fontFamily = fontFamily)
        }

        Spacer(modifier = Modifier.height(50.dp))

        val addview = AdView(context)
        addview.setAdSize(AdSize.LARGE_BANNER)

        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->

                addview.apply {
                    adUnitId = context.getString(R.string.ad_id_banner)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )

    }
}