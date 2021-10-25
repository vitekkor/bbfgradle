// Original bug: KT-38242

import java.io.File
import javax.script.ScriptEngineManager

fun main() {
	with(ScriptEngineManager().getEngineByExtension("kts")) {
		kotlin.run {
			this.eval(File("E:\\Sync\\Insignia\\game-server\\scripts\\skills\\woodcutting\\Woodcutting.kts").readText())
		}
	}
}
