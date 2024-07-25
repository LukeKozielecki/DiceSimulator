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
}