// Original bug: KT-40337

// @TODO: Kotlin 1.4-M3 regression bug
//> Task :korte:compileKotlinJvm FAILED
//e: /home/soywiz/projects/korlibs/korge-next/korte/src/commonMain/kotlin/com/soywiz/korte/Templates.kt: (37, 4): Suspend function 'invoke' should be called only from a coroutine or another suspend function
internal class Demo() {
	suspend operator fun <T> invoke(name: String, block: suspend () -> T): T {
		TODO()
	}
}

suspend fun demo(callback: suspend () -> Unit) = when {
	true -> {
		val demo = Demo()
		demo("test") { callback() }
	}
	else -> TODO()
}
