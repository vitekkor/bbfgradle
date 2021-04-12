// Original bug: KT-4060

import java.util.concurrent.CompletableFuture
import java.util.function.Function
import java.util.function.Consumer

fun main(args: Array<String>) {
	val future = CompletableFuture.supplyAsync {
		"test"
	}!!

	future.thenRun(Runnable {"test1"}) // Ok
	future.thenRun {"test1"} // Ok

	future.thenAccept(Consumer<String> {}) // Unresolved reference: Consumer
	future.thenAccept({}) // Type mismatch

	future.thenApply(Function<String, Unit> {}) // Unresolved reference: Function
	future.thenApply({}) // Type mismatch
}
