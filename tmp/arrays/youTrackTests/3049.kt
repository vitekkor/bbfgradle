// Original bug: KT-26345

import java.util.stream.Collectors
import java.util.function.Function

fun main(args: Array<String>) {
    emptyList<String>()
        .stream()
        .collect(
            Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            )
        )
}
