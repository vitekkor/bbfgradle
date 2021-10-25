// Original bug: KT-23417

package com.github.vmironov

fun main(args: Array<String>) {
  CrashMe().crashMe()
}

class CrashMe {
  fun crashMe() {
    "crashMe".let {
      object : Any() {
        override fun toString() = this@CrashMe.toString()
      }
    }.toString()
  }
}
