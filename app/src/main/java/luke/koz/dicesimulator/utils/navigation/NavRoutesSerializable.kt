package luke.koz.dicesimulator.utils.navigation

import kotlinx.serialization.Serializable

/**
 * NavRoutesSerializable class stores Serializable routes utilized inside AppNavigationHost
 */
sealed class NavRoutesSerializable{

    @Serializable
    object DiceHubLocation

    @Serializable
    object DiceScreen

    @Serializable
    object DiceScreenD4

    @Serializable
    object DiceScreenD6

    @Serializable
    object DiceScreenD12

    @Serializable
    object DiceScreenD20

}