package luke.koz.dicesimulator.dicesimulator.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import luke.koz.dicesimulator.R
import luke.koz.dicesimulator.dicesimulator.viewmodel.DiceState
import luke.koz.dicesimulator.dicesimulator.viewmodel.DiceThrow
import luke.koz.dicesimulator.dicesimulator.viewmodel.DiceUiState
import luke.koz.dicesimulator.dicesimulator.viewmodel.DiceViewModel
import luke.koz.dicesimulator.ui.theme.DiceSimulatorTheme

@Composable
fun DiceScreen(
    modifier: Modifier = Modifier,
    diceType: DiceState,
    numberOfCoinStates : Int,
    diceViewModel: DiceViewModel = viewModel()
) {
    val diceUiState by diceViewModel.diceUiState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.ic_dice_app_background_jpg),
                contentScale = ContentScale.FillBounds
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DiceCompleteElement(
            onClickListener = {
                //old way
                //diceViewModel.recalculateDiceState(numberOfCoinStates)
                //diceViewModel.getDiceType(diceType = diceType)

                diceViewModel.getDrawableForDiceType(diceType, numberOfCoinStates)
            },
            diceViewModel = diceViewModel,
            resultInt = diceUiState.result,
            diceUiState = diceUiState,
        )
    }
}

@Composable
fun DiceCompleteElement(
    modifier: Modifier = Modifier,
    diceViewModel: DiceViewModel,
    onClickListener: () -> Unit,
    resultInt : Int,
    diceUiState: DiceUiState,
) {
    Box(modifier = modifier
        .aspectRatio(1f)
        .wrapContentSize(Center)) {
        Card(
            modifier = modifier
                .minimumInteractiveComponentSize()
                .padding(60.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = diceUiState.diceType.toString())
                DiceImage(
                    onClickListener = onClickListener,
                    diceUiState = diceUiState,
                    resultInt = resultInt,
                    backgroundPainterResource = diceUiState.coinUiStateBackground,
                    foregroundPainterResource = diceUiState.coinUiStateForeground,//diceViewModel.fetchCoinRes(diceUiState.diceType, false),
                    modifier = modifier.padding(vertical = 60.dp)

                )
                val displayValue = diceUiState.result + 1
                Text(text = displayValue.toString())
            }
        }
    }
}

@Composable
fun DiceImage(
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit,
    diceUiState : DiceUiState,
    resultInt : Int,
    backgroundPainterResource : Int,
    foregroundPainterResource : Int
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    Box (modifier = Modifier
        .onSizeChanged {
            size = it
        }){
        Image(
            painter = painterResource(id = backgroundPainterResource),
            contentDescription = null,
            modifier = modifier
                .align(Center)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
        )
        Row (modifier = Modifier
            .then(
                with(LocalDensity.current) {
                    Modifier.size(
                        width = size.width.toDp(),
                        height = size.height.toDp(),
                    )
                }
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            if (diceUiState.diceType == DiceThrow.Coin) {
                Image(
                    painter = painterResource(id = foregroundPainterResource),
                    contentDescription = null,
                    modifier = modifier
                        .weight(1f)
                        .clip(CircleShape)
                        .size(108.dp),
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(resultInt+1),
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(count = resultInt + 1) {
                        Image(
                            painter = painterResource(id = foregroundPainterResource),
                            contentDescription = null,
                            modifier = modifier
                                .weight(1f)
                                .clip(CircleShape)
                                .size(108.dp / (resultInt + 1)),
                        )
                    }
                }
            }
        }
        if (diceUiState.diceType == DiceThrow.Blank) {
            Image(
                painter = painterResource(id = R.drawable.ic_dice_app),
                contentDescription = null,
                modifier = modifier
                    .align(Center)
                    .clip(CircleShape)
                    .clickable {
                        onClickListener()
                        Log.d("CoinToss", "$resultInt")
                    }
                    .size(108.dp)
                    .border(2.dp, Color.White, CircleShape)
            )
        }
        Image(
            painter = painterResource(id = foregroundPainterResource),
            contentDescription = null,
            modifier = modifier
                .align(Center)
                .clip(CircleShape)
                .clickable {
                    onClickListener()
                    Log.d("CoinToss", "$resultInt")
                }
                .size(108.dp)
                .alpha(0.0f)
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
            resultInt = 1,
            diceUiState = diceUiState,
        )
    }
}

@Preview
@Composable
private fun DiceScreenPreview() {
    val viewModel : DiceViewModel = viewModel()
    val diceUiState by viewModel.diceUiState.collectAsState()
    DiceSimulatorTheme {
        DiceScreen(
            diceType = diceUiState.diceType,
            numberOfCoinStates = 2)
    }
}