// Original bug: KT-21727

annotation class WithVarargArray(vararg val value: String)
@WithVarargArray(*arrayOf("a", "b")) class RedundantCall
@WithVarargArray(*["a", "b"]) class RedundantLiteral 