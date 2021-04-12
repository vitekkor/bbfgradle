// Original bug: KT-30516

class KotlinNativeFramework {
  fun helloFromKotlin() : String {

    var x = 0
    repeat(10) {
      x++
    }

    return "Hello from Kotlin! $x"
  }
}
