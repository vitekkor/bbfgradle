// Original bug: KT-16145

// FAILS: Internal error: wrong code generated
suspend fun String.ext(): Int = 10
suspend fun coroutinebug(v: String?) {
	v?.ext()
}

// WORKS:
//suspend fun String.ext(): Int = 10
//suspend fun coroutinebug(v: String?) {
//	@Suppress("IfThenToSafeAccess")
//	if (v != null) v.ext()
//}

// WORKS:
//suspend fun String.ext(): Int = 10
//suspend fun coroutinebug(v: String?) = v?.ext()
