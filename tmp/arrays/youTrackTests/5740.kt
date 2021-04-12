// Original bug: KT-21681

fun testConditionalBreakpoint(password: String? = null){
    // conditional breakpoint: password.isNullOrBlank
    password.isNullOrBlank() // BP doesn't work if `password` = `null`

    // conditional breakpoint: password.contains("1")
    password != null && password.contains("1") // BP doesn't work if `password` contains `1`

    // conditional breakpoint: password == null
    password == null // BP works OK

    fun isNull(value: String?) = value.isNullOrBlank()
    // conditional breakpoint: isNull(password)
    isNull(password) // BP works OK
}
