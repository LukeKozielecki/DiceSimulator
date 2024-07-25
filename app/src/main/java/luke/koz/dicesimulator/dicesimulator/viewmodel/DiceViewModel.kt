package luke.koz.dicesimulator.dicesimulator.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import luke.koz.dicesimulator.R
import kotlin.random.Random

interface DiceState

/**
 * DiceThrow sealed class serves as a collection of possible DiceStates. It is intended to function
 * as a way of mitigating possible typo mistakes during programming and refactoring of app features
 */
sealed class DiceThrow : DiceState {
    data object Heads : DiceThrow()
    data object Tails : DiceThrow()
    data object Blank : DiceThrow()
}

/**
 * Data class created to represent ui state of DiceScreen.kt
 * */
data class DiceUiState(
    var coinUiStateBackground : DiceState = DiceThrow.Blank,
    var coinUiStateForeground : DiceState = DiceThrow.Blank
)

/**
 * ViewModel used for business logic inside DiceScreen.kt
 * */
class DiceViewModel : ViewModel() {

    private var _diceUiState = MutableStateFlow(DiceUiState())
    val diceUiState : StateFlow<DiceUiState> = _diceUiState.asStateFlow()

    init {
        resetDice()
    }

    /**
     * Function recalculating coin state assigning a value to uiState variable
     */
    fun recalculateDiceState (numberCoinStates : Int) {
        when (Random.nextInt(numberCoinStates)) {
            0 -> _diceUiState.value = DiceUiState(DiceThrow.Heads)
            else -> _diceUiState.value = DiceUiState(DiceThrow.Tails)
        }
    }

    /**
     * Function resetting the coin to default state when user enters the DiceScreen of coin variant
     */
    private fun resetDice() {
        _diceUiState.value = DiceUiState(DiceThrow.Blank)
    }

    /**
     * Function responsible for loading drawable assets into the ui
     */
    fun fetchCoinRes (diceState: DiceState, isForegroundBoolean: Boolean) : Int {
        return if (isForegroundBoolean) {
            when (diceState) {
                DiceThrow.Heads -> R.drawable.ic_coin_heads_background
                DiceThrow.Tails -> R.drawable.ic_coin_tails_background
                DiceThrow.Blank -> R.drawable.ic_coin_pre_roll_foreground
                else -> R.drawable.ic_launcher_background
            }
        } else {
            when (diceState) {
                DiceThrow.Heads -> R.drawable.ic_coin_heads_android
                DiceThrow.Tails -> R.drawable.ic_coin_tails_bitcoin
                DiceThrow.Blank -> R.drawable.ic_coin_pre_roll_background
                else -> R.drawable.ic_launcher_foreground
            }
        }
    }
}