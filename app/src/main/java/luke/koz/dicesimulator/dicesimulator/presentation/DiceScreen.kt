package luke.koz.dicesimulator.dicesimulator.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import luke.koz.dicesimulator.dicesimulator.viewmodel.DiceState
import luke.koz.dicesimulator.dicesimulator.viewmodel.DiceViewModel
import luke.koz.dicesimulator.ui.theme.DiceSimulatorTheme

@Composable
fun DiceScreen(
    modifier: Modifier = Modifier,
    diceViewModel: DiceViewModel = viewModel()
) {
    val diceUiState by diceViewModel.diceUiState.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DiceCompleteElement(
            onClickListener = {
                diceViewModel.recalculateDiceState(2)
            },
            diceViewModel = diceViewModel,
            coinUiStateBackground = diceUiState.coinUiStateBackground,
            coinUiStateForeground = diceUiState.coinUiStateBackground,
        )
    }
}

@Composable
fun DiceCompleteElement(
    modifier: Modifier = Modifier,
    diceViewModel: DiceViewModel,
    onClickListener: () -> Unit,
    coinUiStateBackground: DiceState,
    coinUiStateForeground: DiceState
) {
    Box(modifier = modifier
        .aspectRatio(1f)
        .wrapContentSize(Center)) {
        Card(
            modifier = modifier
                .minimumInteractiveComponentSize()
                .padding(60.dp)
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DiceImage(
                    onClickListener = onClickListener,
                    backgroundPainterResource = diceViewModel.fetchCoinRes(coinUiStateBackground, true),
                    foregroundPainterResource = diceViewModel.fetchCoinRes(coinUiStateForeground, false),
                    modifier = modifier.padding(vertical = 60.dp)
                )
            }
        }
    }
}

@Composable
fun DiceImage(
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit,
    backgroundPainterResource : Int,
    foregroundPainterResource : Int
) {
    Box {
        Image(
            painter = painterResource(id = backgroundPainterResource),
            contentDescription = null,
            modifier = modifier
                .align(Center)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
        )
        Image(
            painter = painterResource(id = foregroundPainterResource),
            contentDescription = null,
            modifier = modifier
                .align(Center)
                .clip(CircleShape)
                .clickable {
                    onClickListener()
                    Log.d("CoinToss", "Coin was pressed")
                }
                .size(108.dp)
        )
    }
}

@Composable
fun DiceRollResultHistory(modifier: Modifier = Modifier, resultsList: MutableList<String>) {
    Box(modifier = modifier) {
        if (resultsList.isNotEmpty()) {
            LazyColumn(modifier = modifier) {
                items(resultsList.size) {
                    Text(text = resultsList[it])
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun DiceRollResultHistoryPreview() {
    val interimResultsList : MutableList<String> = mutableListOf(
        "Heads",
        "Tails",
        "Heads",
        "Heads",
    )
    DiceRollResultHistory(resultsList = interimResultsList)
}

@Preview (showBackground = false)
@Composable
private fun DiceCompleteElementPreview() {
    val viewModel : DiceViewModel = viewModel()
    val diceUiState by viewModel.diceUiState.collectAsState()
    DiceSimulatorTheme {
        DiceCompleteElement(
            onClickListener = {
                Log.d("CoinToss", "Preview coin was tapped")
            },
            diceViewModel = viewModel,
            coinUiStateBackground = diceUiState.coinUiStateBackground,
            coinUiStateForeground = diceUiState.coinUiStateBackground,
        )
    }
}

@Preview
@Composable
private fun DiceScreenPreview() {
    DiceSimulatorTheme {
        DiceScreen()
    }
}