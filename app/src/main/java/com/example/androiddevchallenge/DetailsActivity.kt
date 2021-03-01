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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

class DetailsActivity : AppCompatActivity() {

    companion object {
        fun actionStart(pet: Pet) {

            val context = BaseApplication.getContext()
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("name", pet.name)
            intent.putExtra("gender", pet.gender)
            intent.putExtra("variety", pet.variety)
            intent.putExtra("description", pet.description)
            intent.putExtra("image", pet.image)

            ContextCompat.startActivity(context, intent, null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("name")
        name ?: finish()
        val gender = intent.getIntExtra("gender", 0)
        if (gender == 0) finish()
        val variety = intent.getStringExtra("variety")
        variety ?: finish()
        val description = intent.getStringExtra("description")
        description ?: finish()
        val image = intent.getStringExtra("image")
        image ?: finish()

        val pet = Pet(name!!, gender, variety!!, description!!, ArrayList<String>(), image!!)
        setContent {
            MyTheme {
                DetailsMyApp(pet)
            }
        }
    }
}

data class UserInfo(val name: String, val avatar: String)

@Composable
fun LabelTab(
    title: String,
    color: Color = Color(0xff5566FF),
    backgroundColor: Color = Color(0x405566FF),
) {
    Box(modifier = Modifier.padding(end = 5.dp)) {
        Text(
            text = title,
            color = color,
            fontSize = 12.sp,
            modifier = Modifier
                .background(
                    backgroundColor,
                    RoundedCornerShape(50)
                )
                .padding(vertical = 3.dp, horizontal = 10.dp)

        )
    }
}

@Composable
fun DetailsUserCard(
    modifier: Modifier? = Modifier,
    user: UserInfo,
    description: String = "…",
) {
    Column(modifier = modifier!!) {
        Row() {
            Column() {

                GlideImage(
                    data = user.avatar,
                    contentDescription = "avatar image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clip(RoundedCornerShape(20.dp)),
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)
            ) {
                Text(text = "PBK Bin", color = Color(0xFF000000), fontSize = 14.sp)
                Text(text = "Publisher", color = Color(0x30000000), fontSize = 12.sp)
            }

            Column() {
                Text(text = "1 day ago", color = Color(0x60000000), fontSize = 12.sp)
            }
        }

        Text(
            text = "Description：$description",
            color = Color(0x60000000),
            fontSize = 12.sp,
            lineHeight = 18.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun PreviewImages(images: List<String>) {
    Row(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        for (image in images) {
            GlideImage(
                data = image,
                contentDescription = "review image",
                requestBuilder = {
                    val options = RequestOptions()
                    options.centerCrop()
                    apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                },
                modifier = Modifier
                    .height(110.dp)
                    .padding(start = 15.dp)
            )
        }
    }
}

@Composable
fun DetailsMyApp(pet: Pet) {

    val isLike = remember {
        mutableStateOf(false)
    }

    Column(Modifier.verticalScroll(rememberScrollState())) {
        Column {

            GlideImage(
                data = pet.image,
                contentDescription = "review image",
                requestBuilder = {
                    val options = RequestOptions()
                    options.centerCrop()
                    apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.FillWidth
            )
        }

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight()
                .offset(y = (-15).dp)
        ) {

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(topStartPercent = 100, topEndPercent = 100)
                    )
            )

            Column(modifier = Modifier.padding(horizontal = 15.dp)) {

                Row() {
                    Column(modifier = Modifier.weight(0.7f)) {
                        Text(text = pet.name, fontWeight = FontWeight.Bold, color = Color.Black)

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 5.dp)
                        ) {

                            Image(
                                painter = if (pet.gender == 1) {
                                    painterResource(R.drawable.ic_item_gender_male)
                                } else {
                                    painterResource(R.drawable.ic_item_gender_female)
                                },
                                contentDescription = "",
                                modifier = Modifier
                                    .width(18.dp)
                                    .height(18.dp)
                                    .padding(end = 5.dp)
                            )

                            Text(
                                text = pet.variety,
                                color = if (pet.gender == 1) {
                                    Color(0x905b79fe)
                                } else {
                                    Color(0x90f66c63)
                                },
                                fontSize = 14.sp
                            )
                        }
                    }

                    Column(modifier = Modifier.weight(0.3f), horizontalAlignment = Alignment.End) {
                        Image(
                            painter = if (isLike.value) {
                                painterResource(id = R.drawable.ic_item_heart)
                            } else {
                                painterResource(id = R.drawable.ic_heart_outline)
                            },
                            contentDescription = "",
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp)
                                .clickable {
                                    isLike.value = !isLike.value
                                },
                        )
                    }
                }

                Row(modifier = Modifier.padding(top = 15.dp)) {
                    LabelTab(
                        title = "Vaccinated",
                        color = Color(0xFF5566FF),
                        backgroundColor = Color(0x405566FF)
                    )

                    LabelTab(
                        title = "Deworming",
                        color = Color(0xFFfe766c),
                        backgroundColor = Color(0x40fe766c)
                    )
                }
            }

            PreviewImages(images = getImageTestData(pet.image))

            Divider(color = Color(0xFFF2F2F2), modifier = Modifier.padding(horizontal = 15.dp))

            DetailsUserCard(
                Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
                UserInfo("好傻好天真", "http://cos.haxibiao.com/images/60371a662a039.png"),
                description = pet.description
            )
        }
    }
}

fun getImageTestData(image: String = "http://cos.haxibiao.com/images/60371a662a039.png"): List<String> {
    val images = ArrayList<String>()
    images.add(image)
    images.add(image)
    images.add(image)
    images.add(image)
    images.add(image)
    images.add(image)
    images.add(image)
    images.add(image)
    images.add(image)
    return images
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DetailsLightPreview() {
    MyTheme {
        DetailsMyApp(Pet("", 1, "短腿猫", "", ArrayList<String>(), ""))
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DetailsDarkPreview() {
    MyTheme(darkTheme = true) {
        DetailsMyApp(Pet("", 1, "", "", ArrayList<String>(), ""))
    }
}
