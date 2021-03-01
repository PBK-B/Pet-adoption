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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun PetCard(pet: Pet, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier.clickable {
            DetailsActivity.actionStart(pet)
        }
    ) {
        Column(
            Modifier
                .width(110.dp)
                .height(80.dp),
        ) {
//            Image(
//                painter = painterResource(R.drawable.ic_launcher_background),
//                contentDescription = "",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//                    .clip(RoundedCornerShape(7.dp)),
//                contentScale = ContentScale.FillBounds
//            )
            GlideImage(
                data = pet.image,
                contentDescription = "review image",
                modifier = Modifier.clip(RoundedCornerShape(5.dp)),
            )
        }
        Column(Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = pet.name,
                color = Color(0xFF000000),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 5.dp)
                    .fillMaxWidth()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = if (pet.gender == 1) {
                        painterResource(R.drawable.ic_item_gender_male)
                    } else {
                        painterResource(R.drawable.ic_item_gender_female)
                    },
                    contentDescription = "",
                    modifier = if (pet.gender == 1) {
                        Modifier
                            .width(16.dp)
                            .height(16.dp)
                            .padding(end = 5.dp)
                    } else {
                        Modifier
                            .width(17.dp)
                            .height(17.dp)
                            .padding(end = 5.dp)
                    }
                )

                Text(
                    text = pet.variety,
                    color = if (pet.gender == 1) {
                        Color(0x905b79fe)
                    } else {
                        Color(0x90f66c63)
                    },
                    fontSize = 12.sp
                )
            }

            Text(
                text = pet.description,
                color = Color(0x60000000),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 3.dp),
                overflow = TextOverflow.Clip,
                maxLines = 2
            )
        }
    }
}
