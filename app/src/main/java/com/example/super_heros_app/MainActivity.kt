package com.example.super_heros_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.super_heros_app.ui.theme.AppTheme
import com.example.super_heros_app.ui.theme.Shape
import com.example.super_heros_app.ui.theme.model.Hero
import com.example.super_heros_app.ui.theme.model.heros
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),

                        color = MaterialTheme.colorScheme.background,

                    ) {
                        HeroApp()

                    }

            }
        }
    }
}

@Composable
fun HeroApp( modifier: Modifier = Modifier) {

    Scaffold(
        modifier = Modifier,
        topBar = {
            HeroTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(heros) { hero ->
                HeroCard(
                    hero = hero,
                    name = hero.name,
                    description = hero.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopAppBar(){
    CenterAlignedTopAppBar(
        modifier = Modifier,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(8.dp)
            )
        },
    )
}
@Composable
fun HeroCard(
    hero: Hero,
    @StringRes name : Int,
    @StringRes description: Int,
    contentpadding : PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            ){

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = name),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(top = 8.dp),
                )
                Text(
                    text = stringResource(id = description),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier,
                    overflow = TextOverflow.Ellipsis
                )
            }
            //HeroInfo(hero.name, hero.description, modifier = Modifier)
            Spacer(Modifier.width(16.dp))

            HeroImage(hero.imageID, modifier = Modifier)
        }
    }
}

@Composable
fun HeroImage(
    @DrawableRes imageID: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .size(72.dp)
        .clip(RoundedCornerShape(8.dp))
    ){
        Image(
            painter = painterResource(id = imageID),
            contentDescription = null,
            modifier = Modifier
                .clip(shape = Shape.small),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center
        )
    }
}

@Composable
fun HeroInfo(

    modifier: Modifier = Modifier
) {

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme(darkTheme = false) {
        HeroApp()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkThemePreview() {
    AppTheme(darkTheme = true) {
        HeroApp()
    }
}