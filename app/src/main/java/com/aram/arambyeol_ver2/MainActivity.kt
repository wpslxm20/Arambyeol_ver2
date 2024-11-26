package com.aram.arambyeol_ver2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aram.arambyeol_ver2.ui.theme.Arambyeol_ver2Theme
import com.aram.arambyeol_ver2.viewmodel.MealPlanViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Arambyeol_ver2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }


    }

    @Composable
    fun MainView(viewModel: MealPlanViewModel = hiltViewModel()) {
        viewModel.getMealPlanToNetwork()
        val mealPlan = viewModel.mealPlan.observeAsState()

        Log.d(TAG, "mealPlan : ${mealPlan.value}")
    }

    companion object {
        const val TAG = "MainActivity"
    }
}

