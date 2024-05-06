package com.example.foodappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodappcompose.data.BottomMenuContent
import com.example.foodappcompose.data.CategoryGrid
import com.example.foodappcompose.ui.theme.AvatarBackground
import com.example.foodappcompose.ui.theme.FoodAppComposeTheme
import com.example.foodappcompose.ui.theme.GreyBackground
import com.example.foodappcompose.ui.theme.OrangeBold


@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppComposeTheme {
                Box(
                    modifier = Modifier.background(OrangeBold.copy(alpha = 0.1f))
                        .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
                        .fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        HeaderView()
                        SearchBar()
                        TodayQuiz()
                        CategoryView(
                            listOf(
                                CategoryGrid(
                                    title = "FOOD DELIVERY", description = "FROM RESTAURANTS",
                                    offer= "Min $100 Off", duration = "", iconId =  R.drawable.pizza_food,
                                ),
                                CategoryGrid(
                                    title = "INSTAMART", description = "INSTAMART GROCERY",
                                    offer= "Free Delivery", duration = "", iconId =  R.drawable.insta_mart,
                                ),
                                CategoryGrid(
                                    title = "DINEOUT", description = "EAT OUT & SAVE MORE",
                                    offer= "Upto 50% Off", duration = "", iconId =  R.drawable.dine_out,
                                ),
                                CategoryGrid(
                                        title = "MINIS", description = "UNIQUE FINDS",
                                    offer= "Clear Sale", duration = "", iconId =  R.drawable.mini_out,
                                ),
                                CategoryGrid(
                                    title = "MEDICINE DELIVERY", description = "FROM PHARMACY",
                                    offer= "Min $100 Off", duration = "", iconId =  R.drawable.medicine,
                                ),
                                CategoryGrid(
                                    title = "BOOK MEAL", description = "ON RESTAURANT",
                                    offer= "Free Delivery", duration = "", iconId =  R.drawable.book_meal,
                                ),
                            )
                        )
                    }
                    BottomBar()
                }
            }
        }
    }
}

@Composable
fun HeaderView(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth().weight(4.0f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.LocationOn,
                    tint = OrangeBold,
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.home),
                    modifier = modifier.padding(start = 4.dp, end = 4.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Icon(
                    Icons.Rounded.KeyboardArrowDown,
                    tint = Color.Gray,
                    contentDescription = null,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.home_address),
                modifier = modifier.padding(start = 6.dp, end = 4.dp).fillMaxWidth(),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth().weight(1.0f).padding(top = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(AvatarBackground)
            ) {
                Icon(
                    Icons.Rounded.Person,
                    tint = GreyBackground,
                    contentDescription = null,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun SearchBar() {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Box(modifier = Modifier.padding(top = 24.dp)) {
        TextField(
            value = text,
            placeholder = {
                Text("Search for 'Cake'", style = TextStyle(color = Color.Gray))
            },
            colors = TextFieldDefaults.colors(
                cursorColor = OrangeBold,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(onDone = {}),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { value ->
                text = value
            },
            trailingIcon = {
                Row(modifier = Modifier.padding(end = 16.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(4.dp)
                            .width(1.dp)
                            .height(20.dp)
                    )
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Search Icon",
                        tint = OrangeBold
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Green)
                .border(0.5.dp, Color.Gray, RoundedCornerShape(10.dp))

        )
    }

}

@Composable
fun TodayQuiz(modifier: Modifier = Modifier) {
    Row(
        modifier.padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth().weight(3.0f).padding(top = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.today_quiz),
                modifier = modifier.padding(start = 4.dp, end = 4.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = stringResource(id = R.string.answer_win),
                modifier = modifier.padding(start = 6.dp, end = 4.dp, top = 6.dp).fillMaxWidth(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Button(
                modifier = Modifier.padding(top = 16.dp, end = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangeBold),
                onClick = { /* Do something! */ },
                shape = RoundedCornerShape(
                    50,
                    50,
                    50,
                    50
                )
            ) {
                Text("Play now")
            }
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth().weight(2.0f).padding(top = 12.dp)
        ) {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.quiz_card),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun CategoryView(categoryList: List<CategoryGrid>) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues( bottom = 100.dp)) {
            items(categoryList.size) {
                CategoryItem(modifier = Modifier, categoryList[it])
            }
        }
    }
}

@Composable
fun CategoryItem(modifier: Modifier, categoryGrid: CategoryGrid) {


    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.White)
    ){
        Box(modifier = Modifier.height(200.dp).padding(8.dp)) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text(
                    text = categoryGrid.title,
                    modifier = modifier.padding(start = 4.dp, end = 4.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = categoryGrid.description,
                    modifier = modifier.padding(start = 4.dp, end = 4.dp, top = 6.dp)
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 12   .sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Box( modifier = modifier.padding(8.dp)
                    .clip(RoundedCornerShape(30.dp)).background(OrangeBold.copy(alpha = 0.1f)),){
                    Text(
                        text = categoryGrid.offer,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp),

                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = OrangeBold
                        ),
                    )
                }


                Box(modifier=Modifier.height(125.dp).fillMaxWidth().padding(top = 8.dp)){
                    Image(
                        painter = painterResource(id = categoryGrid.iconId),
                        contentDescription = null,
                        modifier = Modifier.align(alignment = Alignment.BottomEnd)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 0.5.dp, end = 0.5.dp)
    ) {
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Food", R.drawable.ic_food),
                BottomMenuContent("Mart", R.drawable.ic_cart),
                BottomMenuContent("Dineout", R.drawable.ic_dine),
                BottomMenuContent("Card", R.drawable.ic_money),
            ), modifier = Modifier.align(Alignment.BottomCenter).clip(RoundedCornerShape(10.dp))
        )
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    inactiveTextColor: Color = Color.Gray,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(bottom = 16.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}


@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    inactiveTextColor: Color = Color.Gray,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) Color.Transparent else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) Color.Black else inactiveTextColor,
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            text = item.title,
            style = TextStyle(fontSize = 14.sp),
            color = if (isSelected) Color.Black else inactiveTextColor
        )
    }
}

