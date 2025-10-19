package nz.co.test.transactions.ui

import TransactionDetailScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nz.co.test.transactions.ui.home.HomeScreen
import nz.co.test.transactions.ui.theme.TransactionAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            TransactionAppTheme {
                NavHost(navController = navController, startDestination = Route.HomeRoute.route) {
                    composable(Route.HomeRoute.route) {
                        HomeScreen(onTransactionClick = { uiTransaction ->
                            navController.navigate(Route.TransactionDetailRoute.createRoute(uiTransaction))
                        })
                    }
                    composable(Route.TransactionDetailRoute.route) {
                        TransactionDetailScreen()
                    }
                }
            }
        }
    }
}