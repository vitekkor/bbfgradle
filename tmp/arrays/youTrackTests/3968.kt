// Original bug: KT-17968

import java.util.stream.Collectors
import java.util.function.Function

val res = listOf(1, 2, 3, 4)
    .parallelStream()
    .map { it * 3 }
    .filter { it < 20 }
    .collect(Collectors.groupingByConcurrent(Function<Int, String> { if (it % 2 == 0) "even" else "odd" }))
