package luke.koz.dicesimulator.dicehublocation.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import luke.koz.dicesimulator.R
import luke.koz.dicesimulator.ui.theme.DiceSimulatorTheme

@Composable
fun HubScreen(
    modifier: Modifier = Modifier,
    destinationList: List<() -> Unit>,
    destinationLabelsList: List<String>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HubDestinationElement(
            destinationList = destinationList,
            destinationLabelsList = destinationLabelsList
        )
    }
}

@Composable
fun HubDestinationElement(
    modifier: Modifier = Modifier,
    destinationList: List<() -> Unit>,
    destinationLabelsList: List<String>
) {
    Column(
        Modifier.paint(
            painter = painterResource(R.drawable.ic_dice_app_background_jpg),
            contentScale = ContentScale.FillBounds
        ).fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for (i in destinationList.indices) {
                    if (i == 0) {
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                    Button(
                        onClick = destinationList[i]::invoke,
                        modifier = modifier
                    ) {
                        Text(text = destinationLabelsList[i])
                    }
                    if (i != destinationList.size) {
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HubScreenPreview(modifier: Modifier = Modifier) {
    val hubScreenPreviewDummy : () -> Unit = {}
    DiceSimulatorTheme {
        HubScreen(modifier = modifier.padding(20.dp), destinationList = listOf(hubScreenPreviewDummy), destinationLabelsList = listOf("DiceType"))
    }
}