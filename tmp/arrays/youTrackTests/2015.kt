// Original bug: KT-37694

fun veryLongNameOfFunction(
    veryLongParameter1: String,
    veryLongParameter2: String,
    veryLongParameter3: String,
    veryLongParameter4: String
) {

}

fun main() {
    veryLongNameOfFunction(
        "asdhgadshakjsdhajshdkjasdhkjashdkjasdhkjashdkjasdhjkasd",
        "asdadsaasdasdasdasdasdasdasdasdasdasdasd",
        "asdasdasdasdasdasdasdasdasdasdasdasd",
        "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasd"
    )
}
