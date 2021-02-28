package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.data.DogBean
import com.example.androiddevchallenge.ui.data.MockDogApi
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class DetailActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detail = intent.extras?.get("detail") as DogBean
        setContent {
            MyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Detail(detail = detail)
                }
            }
        }
    }
}

@Composable
fun Detail(detail: DogBean?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp)) {
        detail?.apply {
            head?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CoilImage(
                        data = it,
                        contentDescription = name,
                        modifier = Modifier.size(100.dp)
                    )
                    Text(name!!, style = MaterialTheme.typography.h6, overflow = TextOverflow.Ellipsis)
                }
            }
            character?.let {
                Text(it, style = MaterialTheme.typography.body1)
            }
            pictures?.let {
                HorizontalScrollingList(it)
            }
        }
    }
}

@Composable
fun HorizontalScrollingList(pictures:List<String>) {
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyRow(state = scrollState, contentPadding = PaddingValues(all = 1.dp)) {
        items(items = pictures) { item ->
            CoilImage(
                data = item,
                contentDescription = item,
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.width(2.dp))
        }
    }
}
