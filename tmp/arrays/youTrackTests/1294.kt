// Original bug: KT-23619

inline fun mrun(crossinline s: () -> Unit): () -> String {
    return {
        s();
        { "K" }
    }()
}

var result = "fail"


fun bar(obj: String) =
       //new singleton class would be created after inline
        run {
            mrun {
                obj
            }
        } === run { //and another one here
            mrun {
                obj
            }
        }

fun main(args: Array<String>) {
    print(bar("")) //'false' in new behavior and 'true' in current
}
