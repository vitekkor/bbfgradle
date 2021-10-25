// Original bug: KT-18892

fun foo(a: Any) {
    if (a !is CharSequence) return

    (a as CharSequence).length // "No cast needed"
    (a as? CharSequence)?.length // No warning (sic!)
}
