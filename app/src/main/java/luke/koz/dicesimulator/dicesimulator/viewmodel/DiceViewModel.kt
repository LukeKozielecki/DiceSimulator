package luke.koz.dicesimulator.dicesimulator.viewmodel

import android.util.Log
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
    data object Blank : DiceThrow()
    data object Coin : DiceThrow()
    data object Dice : DiceThrow()
    data object D4 : DiceThrow()
    data object D6 : DiceThrow()
    data object D12 : DiceThrow()
    data object D20 : DiceThrow()
}

/**
 * Data class created to represent ui state of DiceScreen.kt
 * */
data class DiceUiState(
    var diceType : DiceState = DiceThrow.Blank,
    var result: Int = 0,
    var coinUiStateBackground : Int = R.drawable.ic_launcher_background,
    var coinUiStateForeground : Int = R.drawable.ic_coin_heads_android
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
        when (numberCoinStates) {
            4 -> _diceUiState.value = DiceUiState(DiceThrow.D4)
            2 -> _diceUiState.value = DiceUiState(DiceThrow.Coin)
            else -> _diceUiState.value = DiceUiState(DiceThrow.Blank)
        }
    }

    /**
     * Function resetting the coin to default state when user enters the DiceScreen of coin variant
     */
    private fun resetDice() {
        _diceUiState.value = DiceUiState(DiceThrow.Blank)
    }

    fun getDiceType (diceType: DiceState) {
        //_diceUiState.value = DiceUiState(diceType = diceType)
        Log.d("selectDiceResForRandomBackground", _diceUiState.value.diceType.toString())
    }

    /**
     * Function responsible for loading drawable assets into the ui
     */
    fun getDrawableForDiceType(diceType: DiceState, numberOfCoinStates: Int) {
        getDiceType(diceType)
        when (diceType) {
            DiceThrow.Blank -> _diceUiState.value = DiceUiState(coinUiStateForeground = R.drawable.ic_coin_pre_roll_foreground)
            DiceThrow.Coin -> _diceUiState.value =
                DiceUiState(
                    diceType = diceType,
                    result = Random.nextInt(2),
                    coinUiStateForeground = selectDiceResForRandomForeground(2, _diceUiState.value.result),
                    coinUiStateBackground = selectDiceResForRandomBackground(2, _diceUiState.value.result)
                )
            DiceThrow.Dice -> _diceUiState.value =
                DiceUiState(
                    diceType = diceType,
                    result = Random.nextInt(numberOfCoinStates),
                    coinUiStateForeground = selectDiceResForRandomForeground(numberOfCoinStates, _diceUiState.value.result),
                    coinUiStateBackground = selectDiceResForRandomBackground(numberOfCoinStates, _diceUiState.value.result)
                )
            DiceThrow.D4 -> _diceUiState.value =
                DiceUiState(
                    diceType = diceType,
                    result = Random.nextInt(4),
                    coinUiStateForeground = selectDiceResForRandomForeground(4, _diceUiState.value.result),
                    coinUiStateBackground = selectDiceResForRandomBackground(4, _diceUiState.value.result)
                )
            DiceThrow.D6 -> _diceUiState.value =
                DiceUiState(
                    diceType = diceType,
                    result = Random.nextInt(6),
                    coinUiStateForeground = selectDiceResForRandomForeground(6, _diceUiState.value.result),
                    coinUiStateBackground = selectDiceResForRandomBackground(6, _diceUiState.value.result)
                )
            DiceThrow.D12 -> _diceUiState.value =
                DiceUiState(
                    diceType = diceType,
                    result = Random.nextInt(12),
                    coinUiStateForeground = selectDiceResForRandomForeground(12, _diceUiState.value.result),
                    coinUiStateBackground = selectDiceResForRandomBackground(12, _diceUiState.value.result)
                )
            DiceThrow.D20 -> _diceUiState.value =
                DiceUiState(
                    diceType = diceType,
                    result = Random.nextInt(20),
                    coinUiStateForeground = selectDiceResForRandomForeground(20, _diceUiState.value.result),
                    coinUiStateBackground = selectDiceResForRandomBackground(20, _diceUiState.value.result)
                )
            else -> R.drawable.ic_coin_pre_roll_foreground
        }
    }

    /**
     * Auxiliary function responsible for selection of Background drawable asset.
     */
    private fun selectDiceResForRandomBackground(numberOfCoinStates: Int, result: Int) : Int {
        Log.d("selectDiceResForRandomBackground", _diceUiState.value.result.toString())
        return if (numberOfCoinStates == 2) { //Coin
            when (result) {
                0 -> R.drawable.ic_coin_heads_background
                else -> R.drawable.ic_coin_tails_background
            }
        } else { //Dice
            R.drawable.ic_dice_app_background
        }
    }

    /**
     * Auxiliary function responsible for selection of Foreground drawable asset.
     */
    private fun selectDiceResForRandomForeground(numberOfCoinStates: Int, result: Int) : Int {
        return if (numberOfCoinStates == 2) { //Coin
            when (result) {
                0 -> R.drawable.ic_coin_heads_android
                else -> R.drawable.ic_coin_tails_bitcoin
            }
        } else { //Dice
            R.drawable.ic_coin_heads_android
        }
    }
}