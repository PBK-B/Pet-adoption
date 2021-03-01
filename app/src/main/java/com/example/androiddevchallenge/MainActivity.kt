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

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

data class Pet(
    val name: String, // 名称
    val gender: Int, // 性别: 雄性 1, 雌性 2
    val variety: String, // 种类
    val description: String, // 描述
    val label: List<String>, // 标签
)

@Composable
fun AppTab(title: String = "宠物收养") {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFF03DAC5))
            .padding(15.dp)
    ) {
        Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AppBody(pets: List<Pet>) {
    for (pet in pets) {
        Spacer(modifier = Modifier.padding(10.dp))
        PetCard(
            pet = pet, modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 15.dp)
        )
    }
}

@Composable
fun PetCard(pet: Pet, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Column(
            Modifier
                .width(160.dp)
                .height(120.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.FillBounds
            )
        }
        Column(Modifier.padding(vertical = 5.dp, horizontal = 10.dp)) {
            Text(
                text = pet.name,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .fillMaxWidth()
            )
            Text(text = pet.description, color = Color(0x60000000))
        }
    }
}


// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFBFBFB))
        ) {

            AppTab()

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                AppBody(pets = getPets())
            }

            Divider(color = Color(0xFF666666), modifier = Modifier.padding(top = 10.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Button(onClick = {
                    Toast.makeText(BaseApplication.getContext(), "添加失败", Toast.LENGTH_SHORT).show()
                }, modifier = Modifier.padding(15.dp)) {
                    Text(text = "添加宠物")
                }
            }

        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

// Generate Pet Test Data
fun getPets(): List<Pet> {
    val pets = ArrayList<Pet>()

    val label = ArrayList<String>()
    label.add("标签1")
    label.add("标签2")

    for (i in 1..10) {
        pets.add(Pet("宠物" + i, 1, "猫", "这是描述", label))
    }
    return pets
}
