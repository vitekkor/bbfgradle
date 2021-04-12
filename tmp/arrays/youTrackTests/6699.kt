// Original bug: KT-29120

interface AsyncCloseable {
	suspend fun close()
}

inline suspend fun <T : AsyncCloseable, R> T.use(callback: T.() -> R): R { // FAILS
//suspend fun <T : AsyncCloseable, R> T.use(callback: T.() -> R): R { // WORKS
	try {
		return callback()
	} finally {
		close()
	}
}

class Demo : AsyncCloseable {
    override suspend fun close() {
    }
}

suspend fun main() {
    println(Demo().use { 10 })
}
