package com.example.ui_dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ui_dashboard.ui.theme.UI_DashboardTheme
import com.example.ui_dashboard.ui.theme.myGrey
import com.example.ui_dashboard.ui.theme.myRed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UI_DashboardTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DashBoard()
                }
            }
        }
    }
}

@Composable
fun DashBoard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(myGrey),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (redBorder, searchField, promoBanner) = createRefs()

            // 🔴 Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .constrainAs(redBorder) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .background(color = myRed)
            ) {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 24.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .height(60.dp)
                            .weight(0.7f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Zomato",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { }
                    )
                }
            }

            // 🔍 Search Field
            var searchText by rememberSaveable { mutableStateOf("") }

            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text(text = "Search restaurants...") },
                trailingIcon = {
                    Image(
                        painter = painterResource(R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier
                            .size(38.dp)
                            .padding(end = 6.dp)
                    )
                },
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedPlaceholderColor = Color.White
                ),
                modifier = Modifier
                    .constrainAs(searchField) {
                        top.linkTo(redBorder.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(horizontal = 24.dp)
                    .shadow(3.dp, shape = RoundedCornerShape(50.dp))
                    .background(Color.White, CircleShape)
            )

            // 🏷️ Promo Banner
            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(promoBanner) {
                        top.linkTo(searchField.bottom, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(150.dp)
                    .shadow(5.dp, shape = RoundedCornerShape(25.dp))
                    .background(color = myRed)
            ) {
                val (bannerImage, flatText, freeText, couponText) = createRefs()
                Image(modifier = Modifier.constrainAs(bannerImage){
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }, painter = painterResource(id = R.drawable.bannerimg), contentDescription = null)
                Text(
                    text = "FLAT 50% OFF",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .constrainAs(flatText){
                            top.linkTo(parent.top)
                            end.linkTo(bannerImage.start)
                            start.linkTo(parent.start)
                        }
                )
                Text(
                    text = "Free Delivery + 10% Cashback",
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier.constrainAs(freeText){
                        top.linkTo(flatText.bottom)
                        end.linkTo(flatText.end)
                        start.linkTo(flatText.start)
                    }
                )
                Text(
                    text = "Coupon Code: F0D050",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                        .constrainAs(couponText){
                            top.linkTo(freeText.bottom)
                            end.linkTo(freeText.end)
                            start.linkTo(freeText.start)
                        }
                )
                Text(text = "CATEGORIES",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Column(modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.cake), contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        )
                        Text(text="Cake",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top=8.dp),
                            color = Color.Black
                        )
                    }
                    Column(modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.pizza), contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        )
                        Text(text="Pizza",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top=8.dp),
                            color = Color.Black
                        )
                    }
                    Column(modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.sandwiches), contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        )
                        Text(text="Sandwich",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top=8.dp),
                            color = Color.Black
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Column(modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.noodles), contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        )
                        Text(text="Noodles",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top=8.dp),
                            color = Color.Black
                        )
                    }
                    Column(modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.pasta), contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        )
                        Text(text="Pasta",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top=8.dp),
                            color = Color.Black
                        )
                    }
                    Column(modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.biryani), contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        )
                        Text(text="Biryani",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top=8.dp),
                            color = Color.Black
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ){
                    Column(modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.burger), contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        )
                        Text(text="Burger",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top=8.dp),
                            color = Color.Black
                        )
                    }
                    Column(modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.icecream), contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        )
                        Text(text="Ice Cream",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top=8.dp),
                            color = Color.Black
                        )
                    }
                    Column(modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.dalrice), contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        )
                        Text(text="Dal Rice",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top=8.dp),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayDashboard(){
    UI_DashboardTheme{
        DashBoard()
    }
}
