// Original bug: KT-20245

interface ParentInterface {
    val child: ChildInterface
}

interface ChildInterface {
    val field: String
}

fun crash(data: String): ParentInterface =
    Unit.let {
        object : ParentInterface {
            override val child = object : ChildInterface {
                override val field = data
            }
        }
    }

fun main(arg: Array<String>) {
    crash("test")
}
