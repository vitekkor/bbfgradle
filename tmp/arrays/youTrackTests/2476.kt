// Original bug: KT-40631

class Part

class Thing(parts: Set<Part>) {
    /**
     * Empty if [parts] are empty.
     */
    val isEmpty get() = parts.isEmpty()

    val parts: Set<Part> = parts
}
