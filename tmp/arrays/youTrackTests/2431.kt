// Original bug: KT-37200

package test.pkg

inline fun <reified F> ViewModelContext.viewModelFactory(): F {
    return activity as? F ?: throw IllegalStateException("Boo!")
}

sealed class ViewModelContext {
    abstract val activity: Number
}
