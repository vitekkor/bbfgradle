// Original bug: KT-30154

fun test0() {
    val stringToResultOfString: (String) -> Result<String> = Result.Companion::success
    val listOfStrings: List<String> = emptyList()
    val mapListOfStrings: ((String) -> Result<String>) -> List<Result<String>> = listOfStrings::map

    val list1: List<Result<String>> = listOfStrings.map(stringToResultOfString)
    val list2: List<Result<String>> = listOfStrings.map(Result.Companion::success)
    val list3: List<Result<String>> = mapListOfStrings(stringToResultOfString)
    val list4: List<Result<String>> = mapListOfStrings(Result.Companion::success)
}
