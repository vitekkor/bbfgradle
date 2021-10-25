// Original bug: KT-42584

enum class AnEnum {

  A, B
}

enum class AComplexEnum(val arg1: AnEnum = AnEnum.A,
                        val arg2: AnEnum = AnEnum.B) {

  VALUE(arg2 = AnEnum.A, arg1 = AnEnum.B);
}
