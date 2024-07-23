package luke.koz.dicesimulator.dicehublocation.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import luke.koz.dicesimulator.R
import luke.koz.dicesimulator.ui.theme.DiceSimulatorTheme

@Composable
fun HubScreen(modifier: Modifier = Modifier) {
    Column (modifier = modifier) {
        HubDestinationElement()
        HubDestinationElement()
        HubDestinationElement()
    }
}

@Composable
fun HubDestinationElement(modifier: Modifier = Modifier) {
    Card (modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)){
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Go to destination")
            Button(onClick = { /*TODO*/ }, modifier = modifier) {
                Text(text = stringResource(R.string.interim_lorem_ipsum_short))
            }
        }
    }
}

@Preview
@Composable
private fun HubScreenPreview(modifier: Modifier = Modifier) {
    DiceSimulatorTheme {
        HubScreen(modifier = modifier.padding(20.dp))
    }
}