// Original bug: KT-25798

// after conversion should be one of these
// (names are different for copy-pasteability, of course it should stay `extension`)
inline fun no(noinline action: () -> Unit): () -> Unit = { action() }
inline fun cross(crossinline action: () -> Unit): () -> Unit = { action() }
