// Original bug: KT-15483

interface Callback {
  fun onError(throwable: Throwable)
}

class CrashMe {
  init {
    crashMe(Callback::class.java) {
      object : Callback {
        override fun onError(throwable: Throwable) {
          throw UnsupportedOperationException()
        }
      }
    }
  }

  fun <T : Any> crashMe(clazz: Class<T>, factory: () -> T) {
    throw UnsupportedOperationException()
  }
}
