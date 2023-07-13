package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(12.dp)
    ) {
        var imageNumber by remember { mutableStateOf(1) }
        val image: Int
        val imageName: Int
        val imageAuthor: Int

        when (imageNumber) {
            1 -> {
                image = R.drawable.image1
                imageName = R.string.image1_description
                imageAuthor = R.string.image1_author
            }

            2 -> {
                image = R.drawable.image2
                imageName = R.string.image2_description
                imageAuthor = R.string.image2_author
            }

            else -> {
                image = R.drawable.image3
                imageName = R.string.image3_description
                imageAuthor = R.string.image3_author
            }
        }
        ImageWindow(image, Modifier.weight(7f))
        Spacer(modifier = Modifier.height(16.dp))
        ImageDescription(imageName, imageAuthor, Modifier.weight(2f))
        ChangeImageButtons(
            { if (imageNumber < 3) imageNumber++ },
            { if (imageNumber > 1) imageNumber-- },
            Modifier.weight(1f)
        )
    }
}

@Composable
fun ImageWindow(@DrawableRes image: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .shadow(16.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "parrot",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 32.dp)
        )
    }
}

@Composable
fun ImageDescription(
    @StringRes imageName: Int,
    @StringRes imageAuthor: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(start = 8.dp)
    ) {
        Text(
            text = stringResource(id = imageName),
            fontSize = 32.sp,
        )
        Text(
            text = stringResource(id = imageAuthor),
            fontSize = 24.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ChangeImageButtons(
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(140.dp)
        ) {
            Text(text = stringResource(id = R.string.next_button), fontSize = 16.sp)
        }
        Button(
            onClick = onPrevClick,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(140.dp)
        ) {
            Text(text = stringResource(id = R.string.prev_button), fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}