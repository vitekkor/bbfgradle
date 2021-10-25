// Original bug: KT-27523

fun String?.test(testString: String?): String?{
    return testString?.let {
        if (this!= null){
            "return this as not null"
        }else
            null
    }?: null.test("")
}
