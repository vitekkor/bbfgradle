// Original bug: KT-42827

interface A // [JS_NAME_CLASH] JavaScript name (A) generated for this declaration clashes with another declaration: interface A

fun A(): A = AImpl() // [JS_NAME_CLASH] JavaScript name (A) generated for this declaration clashes with another declaration: interface A

private class AImpl : A
