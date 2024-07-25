package luke.koz.dicesimulator.utils.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import luke.koz.dicesimulator.dicehublocation.presentation.HubScreen
import luke.koz.dicesimulator.dicesimulator.presentation.DiceScreen

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    Column (modifier = modifier) {
        NavHost(
            navController = navHostController,
            startDestination = NavRoutesSerializable.DiceHubLocation
        ) {
            composable<NavRoutesSerializable.DiceHubLocation> {
                HubScreen(
                    modifier = modifier,
                    destinationList = listOf {
                        navHostController.navigate(
                            NavRoutesSerializable.DiceScreen
                        )
                    }
                )
            }

            composable<NavRoutesSerializable.DiceScreen> {
                DiceScreen()
            }
        }
    }
}
