// Original bug: KT-36505

fun test(r: Runnable, rr: Runnable) {}

fun usage() {
  test(Runnable {}, Runnable {}) // 1
  test({}, {}) // 2
  test(Runnable {}, {}) // 3
  test({}, Runnable {}) // 4
}
