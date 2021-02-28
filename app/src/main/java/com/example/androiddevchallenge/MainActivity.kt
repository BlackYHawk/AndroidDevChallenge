/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.data.DogBean
import com.example.androiddevchallenge.ui.data.MockDogApi
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.google.android.material.appbar.AppBarLayout
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : AppCompatActivity() {
    private lateinit var dogs:List<DogBean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dogs = MockDogApi().mockDogList(this)
        setContent {
            MyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ScrollingList(this@MainActivity, dogs)
                }
            }
        }
    }
}

@Composable
fun ScrollingList(context: Context, dogs:List<DogBean>) {
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState, contentPadding = PaddingValues(all = 1.dp)) {
        items(items = dogs) { item ->
            DogListItem(context, item)
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
    }
}

@Composable
fun DogListItem(context: Context, item: DogBean) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .height(86.dp)
        .clickable {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("detail", item)
            context.startActivity(intent)
        }) {
        item.head?.let {
            CoilImage(
                data = it,
                contentDescription = item.name,
                modifier = Modifier.size(80.dp)
            )
        }
        Spacer(Modifier.width(2.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(1.dp))
            item.name?.let { Text(it, style = MaterialTheme.typography.h6) }
            Spacer(Modifier.height(1.dp))
            item.character?.let { Text(it, style = MaterialTheme.typography.body2, overflow = TextOverflow.Ellipsis) }
            Spacer(Modifier.height(1.dp))
        }
    }
}