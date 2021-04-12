// Original bug: KT-41632

@Deprecated(message = "So much regret", level = DeprecationLevel.HIDDEN)
fun deprecatedHiddenMethod() { TODO() }
@Deprecated(message = "So much regret", level = DeprecationLevel.WARNING)
fun deprecatedWarningMethod() { TODO() }
