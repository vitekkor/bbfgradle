// Original bug: KT-8152

open class BaseClass {
    protected fun baseMethodToCall() {}
}

public class ChildClass : BaseClass() {
    public fun someMethod() {
        object: AsyncTask<Void, Void, Void>() {
            override fun onPreExecute() {
                super.onPreExecute()
                baseMethodToCall()
            }

        }.execute()
    }
}

open class AsyncTask<T1, T2, T3> {
    open fun onPreExecute() {}
    fun execute() {
        onPreExecute()
    }
}

fun main(args: Array<String>) {
    ChildClass().someMethod()
}
