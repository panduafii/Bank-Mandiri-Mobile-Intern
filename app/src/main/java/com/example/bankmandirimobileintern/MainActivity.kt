package com.example.bankmandirimobileintern

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.bankmandirimobileintern.domain.manager.usecase.AppEntryUseCases
import com.example.bankmandirimobileintern.presentation.navgraph.NavGraph
import com.example.bankmandirimobileintern.presentation.onboarding.OnBoardingScreen
import com.example.bankmandirimobileintern.presentation.onboarding.OnBoardingViewModel
import com.example.bankmandirimobileintern.ui.theme.BankMandiriMobileInternTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var appEntryUseCases: AppEntryUseCases

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { viewModel.splashCondition.value })
        }

//        lifecycleScope.launch {
//            appEntryUseCases.readAppEntry().collect{
//                Log.d("Test", it.toString())
//            }
//        }


        setContent {
            BankMandiriMobileInternTheme (dynamicColor = false) {

                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
//                    val viewModel: OnBoardingViewModel = hiltViewModel()
//                    OnBoardingScreen(
//                        onEvent = viewModel::onEvent
//                    )
                    NavGraph(startDestination = viewModel.startDestination.value)
                }
            }
        }
    }
}