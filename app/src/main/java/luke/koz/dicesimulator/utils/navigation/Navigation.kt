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
import luke.koz.dicesimulator.dicesimulator.viewmodel.DiceThrow

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
                val diceScreen =
                    {
                        navHostController.navigate(
                            NavRoutesSerializable.DiceScreen
                        )
                    }
                val diceScreenD4 =
                    {
                        navHostController.navigate(
                            NavRoutesSerializable.DiceScreenD4
                        )
                    }
                val diceScreenD6 =
                    {
                        navHostController.navigate(
                            NavRoutesSerializable.DiceScreenD6
                        )
                    }
                val diceScreenD12 =
                    {
                        navHostController.navigate(
                            NavRoutesSerializable.DiceScreenD12
                        )
                    }
                val diceScreenD20 =
                    {
                        navHostController.navigate(
                            NavRoutesSerializable.DiceScreenD20
                        )
                    }
                HubScreen(
                    modifier = modifier,
                    destinationList = listOf(
                        diceScreen,
                        diceScreenD4,
                        diceScreenD6,
                        diceScreenD12,
                        diceScreenD20,
                    ),
                    destinationLabelsList = listOf(
                        "Coin Toss",
                        "D4 Dice",
                        "D6 Dice",
                        "D12 Dice",
                        "D20 Dice",
                    )
                )
            }

            composable<NavRoutesSerializable.DiceScreen> {
                DiceScreen(
                    diceType = DiceThrow.Coin,
                    numberOfCoinStates = 2
                )
            }

            composable<NavRoutesSerializable.DiceScreenD4> {
                DiceScreen(
                    diceType = DiceThrow.D4,
                    numberOfCoinStates = 4
                )
            }

            composable<NavRoutesSerializable.DiceScreenD6> {
                DiceScreen(
                    diceType = DiceThrow.D6,
                    numberOfCoinStates = 6
                )
            }

            composable<NavRoutesSerializable.DiceScreenD12> {
                DiceScreen(
                    diceType = DiceThrow.D12,
                    numberOfCoinStates = 12
                )
            }
            composable<NavRoutesSerializable.DiceScreenD20> {
                DiceScreen(
                    diceType = DiceThrow.D20,
                    numberOfCoinStates = 20
                )
            }
        }
    }
}
