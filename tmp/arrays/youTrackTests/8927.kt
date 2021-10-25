// Original bug: KT-8829

public class ClassA<N> (
        val messenger: ClassB<N> = object : ClassB<N> {
            override public fun methodOne(param: List<N>) {
                println("List")
            }
        }
)

public interface ClassB<N> {
    public fun methodOne(param: List<N>)
}
