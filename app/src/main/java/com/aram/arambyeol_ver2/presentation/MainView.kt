package com.aram.arambyeol_ver2.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aram.arambyeol_ver2.R
import com.aram.arambyeol_ver2.core.domain.entity.DateEnum
import com.aram.arambyeol_ver2.ui.theme.Black3A3A3A
import com.aram.arambyeol_ver2.ui.theme.Gray767676
import com.aram.arambyeol_ver2.ui.theme.GrayA9A9A9

@Composable
fun MainView(viewModel: MealPlanViewModel = hiltViewModel()) {
    viewModel.updateMealPlanToNetwork()
    val mealPlan = viewModel.mealPlan.observeAsState()
    val selectedDay = remember { mutableStateOf(DateEnum.TODAY) }

    Log.d(MainActivity.TAG, "mealPlan : ${mealPlan.value}")

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Surface(
            shape = RectangleShape,
            color = Color.White,
            shadowElevation = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 10.dp)
                .drawWithContent {
                    val paddingPx = 3.dp.toPx()
                    clipRect(
                        top = -paddingPx, // 7
                        left = -paddingPx, // 8
                        right = size.width + paddingPx, // 9
                        bottom = size.height + paddingPx // 10
                    ) { this@drawWithContent.drawContent() }
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TopView()
                DateSelectView(selectedDay)
            }
        }
    }

}

@Composable
fun TopView() {
    Row(
        modifier = Modifier
            .padding(start = 13.dp, top = 5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo), contentDescription = "logo",
            modifier = Modifier
                .width(42.dp)
                .height(46.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Gray767676,
            modifier = Modifier
                .padding(top = 18.dp, start = 3.dp)
        )
    }
}

@Composable
fun DateSelectView(selectedDay: MutableState<DateEnum>) {
    val dateList = listOf(
        DateEnum.TODAY,
        DateEnum.TOMORROW,
        DateEnum.AFTER_TOMORROW
    )

    Column(
        modifier = Modifier.padding(bottom = 14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 14.dp)
        ) {
            dateList.forEach { date ->
                BtnDate(text = date.date, selectedOption = selectedDay.value, onOptionSelected = { selectedDay.value = it })
            }
        }
    }
}

@Composable
fun BtnDate(
    text: String,
    selectedOption: DateEnum,
    onOptionSelected: (DateEnum) -> Unit
) {
    val isSelected = (text == selectedOption.date)

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onOptionSelected(DateEnum.values().first { it.date == text }) })
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val backgroundColor = if (isSelected) Black3A3A3A else Color.Transparent
        val textColor = if (isSelected) Color.White else GrayA9A9A9

        Column(
            modifier = Modifier
                .padding(end = 4.dp)
                .background(backgroundColor, shape = RoundedCornerShape(20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(horizontal = 17.dp, vertical = 7.dp),
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
