// Original bug: KT-37916

enum class MyEnum(val value: String){
    A("a"),
    B("b"),
    C("c");
    
    companion object {
        @Throws(IllegalArgumentException::class)
        fun forValue(value: String): MyEnum {
            val enum = values()
                .find { it.value == value }
            
            return requireNotNull(enum) {
                "Value $value is not valid for MyEnum"
            }
        }
    }
}
