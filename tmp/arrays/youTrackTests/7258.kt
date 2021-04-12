// Original bug: KT-27658

// aplusb.kt
import java.io.File
import java.util.Scanner

fun main(args: Array<String>) {
    with(Scanner(File("aplusb.in"))) {
        File("aplusb.out").printWriter().use { out ->
            out.println(nextInt() + nextInt())
        }
    }
}
