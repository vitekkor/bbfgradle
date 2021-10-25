// Original bug: KT-9679

fun main(args: Array<String>) = DuelModeManager().forceGameSave()

public inline fun blockStage(f: () -> Unit) = f()

class DuelModeManager {
    private var doOnConnectionLoss : () -> Unit = {}
    var a = 5

    fun forceGameSave() {
        blockStage {
            fun showForceUpdateDialog(retryPhase: () -> Unit) {
                if ((a--) > 0) retryPhase()
            }
            fun saveGame() {
                showForceUpdateDialog(::saveGame)
            }

            doOnConnectionLoss = { showForceUpdateDialog(::saveGame) }
            saveGame()
        }
    }
}
