// Original bug: KT-653

    fun toString(part : Any?) : CharSequence? {
      return (if (((part is CharSequence?)))(part as CharSequence?) else part?.toString() as CharSequence?)
    }
