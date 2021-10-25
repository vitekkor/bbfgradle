// Original bug: KT-24264

enum class MyFavouriteEnum {
    A, B
}

typealias TheEnum = MyFavouriteEnum


fun main(args: Array<String>) {
    val x = MyFavouriteEnum.A  // When typing MyFavouriteEnum.<Ctrl-Space> A and B are suggested
    val y = TheEnum.A  // When typing TheEnum.<Ctrl-Space> A and B are NOT suggested
}
