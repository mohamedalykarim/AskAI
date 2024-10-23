package mohalim.ai.askai.ui.main

import android.app.Activity
import android.content.Context
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.sp
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

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = Color(parseColor("#884040")),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp, 0.dp)
                .clickable {
                    val intent = Intent(context, ChatAIActivity::class.java)
                    intent.putExtra(
                        "URL",
                        "https://chatgpt.com/"
                    )
                    context.startActivity(intent)
                }
                .padding(10.dp)

        ) {

            Image(
                painterResource(id = R.drawable.chat_gpt),
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                contentDescription = "Adobe Firefly"
            )

            Text(
                text = "ChatGPT",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(parseColor("#ffffff")),
                textAlign = TextAlign.Center
            )
            Text(
                text = "اسأل الذكاء الاصطناعي",
                fontFamily = fontFamily,
                color = Color(parseColor("#dadada")),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }



        ThreeRow(
            context,
            fontFamily,
            "https://huggingface.co/chat/",
            R.drawable.hugging_face,
            "Hugging Face",
            "أسأل الذكاء الاصطناعي",

            "https://chatlyai.app/",
            R.drawable.chat_gpt,
            "ChatlyAI",
            "أسأل الذكاء الاصطناعي",

            "https://www.aichatting.net/",
            R.drawable.ai_chatting,
            "AIChatting",
            "أسأل الذكاء الاصطناعي",
        )
        ThreeRow(
            context,
            fontFamily,

            "https://poe.com/",
            R.drawable.poe,
            "Poe",
            "أسأل الذكاء الاصطناعي",

            "https://copilot.microsoft.com/",
            R.drawable.copilot,
            "Copilot",
            "أسأل الذكاء الاصطناعي",

            "https://www.perplexity.ai/",
            R.drawable.perplexity,
            "Perplexity",
            "أسأل الذكاء الاصطناعي",
        )

        Spacer(modifier = Modifier.height(10.dp))


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

        Spacer(modifier = Modifier.height(5.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = Color(parseColor("#884040")),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp, 0.dp)
                .clickable {
                    val intent = Intent(context, ChatAIActivity::class.java)
                    intent.putExtra(
                        "URL",
                        "https://gemini.google.com/"
                    )
                    context.startActivity(intent)
                }
                .padding(10.dp)

        ) {

            Image(
                painterResource(id = R.drawable.bard),
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                contentDescription = "Gemini"
            )

            Text(
                text = "Gemini",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(parseColor("#ffffff")),
                textAlign = TextAlign.Center
            )
            Text(
                text = "اسأل الذكاء الاصطناعي",
                fontFamily = fontFamily,
                color = Color(parseColor("#dadada")),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun ThreeRow(
    context: Context,
    fontFamily: FontFamily,
    firstLink: String,
    firstIcon: Int,
    firstTitle: String,
    firstDesc: String,
    secondLink: String,
    secondIcon: Int,
    secondTitle: String,
    secondDesc: String,
    thirdLink: String,
    thirdIcon: Int,
    thirdTitle: String,
    thirdDesc: String
) {
    Row() {
        /** Gemini **/
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 5.dp, top = 5.dp)
                .fillMaxWidth()
                .height(150.dp)
                .weight(1f)
                .background(
                    color = Color(parseColor("#E7CCCC")),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable {
                    val intent = Intent(context, ChatAIActivity::class.java)
                    intent.putExtra("URL", firstLink)
                    context.startActivity(intent)
                }
                .padding(10.dp)

        ) {
            Image(
                painterResource(id = firstIcon),
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                contentDescription = "Google Bard"
            )
            Text(
                text = firstTitle,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(parseColor("#FF2E63EB")),
                textAlign = TextAlign.Center
            )
            Text(
                text = firstDesc,
                fontFamily = fontFamily,
                color = Color(parseColor("#545454")),
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
        }

        /** Chat GPT **/

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 5.dp, top = 5.dp, end = 5.dp)
                .fillMaxWidth()
                .height(150.dp)
                .weight(1f)
                .background(
                    color = Color(parseColor("#E7CCCC")),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable {
                    val intent = Intent(context, ChatAIActivity::class.java)
                    intent.putExtra("URL", secondLink)
                    context.startActivity(intent)
                }
                .padding(10.dp)

        ) {


            Image(
                painterResource(id = secondIcon),
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                contentDescription = "ChatGPT"
            )
            Text(
                text = secondTitle,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(parseColor("#FF2E63EB")),
                textAlign = TextAlign.Center

            )
            Text(
                text = secondDesc,
                fontFamily = fontFamily,
                color = Color(parseColor("#545454")),
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
        }


        /** AI CHATTING **/
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(end = 5.dp, top = 5.dp)
                .fillMaxWidth()
                .height(150.dp)
                .weight(1f)
                .background(
                    color = Color(parseColor("#E7CCCC")),
                    shape = RoundedCornerShape(20.dp)
                )
                .clickable {
                    val intent = Intent(context, ChatAIActivity::class.java)
                    intent.putExtra("URL", thirdLink)
                    context.startActivity(intent)
                }
                .padding(10.dp)
        ) {


            Image(
                painterResource(id = thirdIcon),
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                contentDescription = "AI Chatting"
            )
            Text(
                text = thirdTitle,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(parseColor("#FF2E63EB")),
                textAlign = TextAlign.Center
            )
            Text(
                text = thirdDesc,
                fontFamily = fontFamily,
                color = Color(parseColor("#545454")),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }

}
