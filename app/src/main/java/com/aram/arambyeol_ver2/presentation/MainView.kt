package com.aram.arambyeol_ver2.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.aram.arambyeol_ver2.core.data.dto.Course
import com.aram.arambyeol_ver2.core.data.dto.DayPlan
import com.aram.arambyeol_ver2.core.domain.entity.DateEnum
import com.aram.arambyeol_ver2.core.domain.entity.MealTimeEnum
import com.aram.arambyeol_ver2.ui.theme.Black3A3A3A
import com.aram.arambyeol_ver2.ui.theme.Gray767676
import com.aram.arambyeol_ver2.ui.theme.Gray8A8A8A
import com.aram.arambyeol_ver2.ui.theme.GrayA9A9A9
import com.aram.arambyeol_ver2.ui.theme.GrayEFEFEF

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
        when (selectedDay.value) {
            DateEnum.TODAY -> {
                ShowDayPlan(viewModel.mealPlan.value?.today)
            }
            DateEnum.TOMORROW -> {
                ShowDayPlan(viewModel.mealPlan.value?.tomorrow)
            }
            DateEnum.AFTER_TOMORROW -> {
                ShowDayPlan(viewModel.mealPlan.value?.theDayAfterTomorrow)
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

@Composable
fun ShowDayPlan(dayPlan: DayPlan?) {
    if (dayPlan != null) {
        for (mealTime in MealTimeEnum.values()) {
            Column(
                modifier = Modifier
                    .padding(start = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 2.dp, bottom = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(mealTime.icon), contentDescription = null,
                        modifier = Modifier
                            .width(28.dp)
                            .height(25.dp)
                            .padding(end = 7.dp)
                    )
                    Text(
                        text = mealTime.mealTime,
                        modifier = Modifier
                            .padding(3.dp),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black3A3A3A
                    )
                    Text(
                        text = "운영 시간 ${mealTime.operationTime}",
                        fontSize = 12.sp,
                        color = Gray8A8A8A
                    )
                }

                val menuList = when (mealTime) {
                    MealTimeEnum.MORNING -> dayPlan.morning
                    MealTimeEnum.LUNCH -> dayPlan.lunch
                    MealTimeEnum.DINNER -> dayPlan.dinner
                }

                ShowOneMealPlan(menuList)
            }
        }
    } else {
        // TODO : dayPlan이 null일 경우 예외 처리
    }
}

@Composable
fun ShowOneMealPlan(oneMealPlan: List<Course>) {
    Row(
        modifier = Modifier
            .padding(bottom = 29.dp)
    ) {
        oneMealPlan.forEach { course ->
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp)
                    .background(GrayEFEFEF, shape = RoundedCornerShape(16.dp)),
            ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 11.dp)
                    ) {
                        val courseName = course.course.split("/")
                        Text(
                            text = courseName[0],
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(end = 5.dp),
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = courseName[1],
                            fontSize = 16.sp,
                            modifier = Modifier,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    if (course.menu != null) {
                        Text(
                            text = course.menu.joinToString(", "),
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

