// Original bug: KT-23623

fun completeMeTop() = 0
class SamePlace {
    fun completeMe() = 0
    var completion1 = SamePlace::completeMe // Completion suggests `completeMe`.
    var completion2 = ::completeMe // Completion *does not* suggest `completeMe`.
    var completion3 = ::completeMeTop // Completion does suggest `completeMeTop`.
}
