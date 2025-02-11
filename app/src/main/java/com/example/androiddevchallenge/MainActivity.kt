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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val image: String, // 照片
)

@Composable
fun AppTab(title: String = "Pet adoption") {
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
        Spacer(modifier = Modifier.padding(4.dp))
        PetCard(
            pet = pet,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(15.dp)
        )
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

            Divider(color = Color(0xFF999999))

            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Button(
                    onClick = {
                        Toast.makeText(BaseApplication.getContext(), "添加失败", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(text = "Find out more.")
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

    pets.add(
        Pet(
            "Sasha",
            2,
            "Female Domestic Short Hair Cat",
            "Hi, my name is Sasha and I am 9 years young. My Mum moved to Queensland and knew I wouldn't cope very well with a big car journey and a very strange place, so she asked Peggy's to take me on and find me a new forever home. I am pretty special, my foster Mum says. I had an accident a few years ago and had to have my tail amputated. It just emphasises my cuteness and style and I haven't missed it. I can run and jump and climb just as well as my tailed brothers and sisters. I love cuddles and head butts. I have a quiet purr but a noisy meow.....just so that you know I need something. I can be frightened of strangers but quickly adapt to my surroundings. I can happily play with children as long as I know them. I will tolerate other cats and dogs as long as they don't get aggressive. I am a desexed girl with a shiny coat and a healthy tick from the vet!! If you want a cuddly furbaby who knows how to show you lots of love, and is happy to chill out all day or play with the little ones, I am the girl for you!! If you are a grandmother who wants lots of cuddles but a girl who will love the grandies, I am your girl. Please enquire with my human friends if you think I might be the one!",
            label,
            "https://images.unsplash.com/photo-1601217155753-807e789c59d8?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MjN8fHBldCUyMGFkb3B0aW9ufGVufDB8fDB8&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60"
        )
    )

    pets.add(
        Pet(
            "Unicorn",
            1,
            "Fluffy cuddle tabby",
            "Meet Fluffy Unicorn. Unicorn is absolutely adorable kind loves cuddles. She was found in front of her carer house. Healthy girl, toilet trained. Ready to be adopt.",
            label,
            "https://d1benffgc67l92.cloudfront.net/img/animals/6100108.jpg"
        )
    )

    pets.add(
        Pet(
            "Linus",
            1,
            "Domestic Short Hair",
            "ADOPTION REQUIREMENTS-- NO OTHER PETS PLEASE (I HAVE FIV)-- ADULTS ONLY-- QUIET HOME-- INDOORS ONLYHello, Linus here! It seems as though the beginning of my life was a little bit rough and, because of this, it has taken me quite some time to enjoy the company of humans and be comfortable around them. I was placed in foster care with a lovely carer who accepted me as I was and helped me to become the affectionate and happy man I am today. When I first enter my new home, it is likely that you will find me hiding in or under beds or other furniture, and my new family will need to have patience and give me time to adjust to my home. Sitting near me and talking to me helps, although I will greet you with a hiss at first. When I get to know you though, I will become a little kitty magnet. You get home? I'm there. You sit on the couch? Here I am. Your laps free? Not anymore! I even became a tad playful before my time in foster care ended, so look out!",
            label,
            "https://d1benffgc67l92.cloudfront.net/img/animals/6092793.jpg"
        )
    )

    pets.add(
        Pet(
            "MAMA KITTY",
            2,
            "Domestic Shorthair / Mix",
            "MAMA KITTY is a sweet, 4 year old girl with a super soft coat. She is very shy and will need a very patient person to allow her the time that she’ll need to feel safe and secure. She is a beauty and a very special and sensitive soul. She is great with other cats--we'd love to see her in a home where she can get some individual attention.",
            label,
            "https://g.petango.com/photos/401/60b7bdea-b694-42de-a43d-31872d7b2ba7.jpg"
        )
    )

    pets.add(
        Pet(
            "Sunny",
            2,
            "Domestic Shorthair / Mix",
            "Meet the ever-glamorous Sunny. Like most celebrities, she’s reclusive and prefers a quiet environment. Elizabeth is also very shy and will require someone with patience. However, she is not shy when it’s time for cat treats! Elizabeth is a strict pescatarian, enjoying the finest fish and seafood. She also enjoys living her best life playing with her favorite toy balls. This exquisite girl has the most beautiful markings with a black whip-like tail and bangs. She is a glamorous girl and is looking for her up upscale forever home.",
            label,
            "https://g.petango.com/photos/401/ef5ae40c-07d8-4532-b415-8df57a991048_TN1.jpg"
        )
    )

    return pets
}
