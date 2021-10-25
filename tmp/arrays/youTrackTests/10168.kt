// Original bug: KT-8836

package test.provider

public object Provider {

    public val doActionAsPublicVal: ActionContext.(name: String) -> Unit = { name -> println("Action ${type}.${name}")}

    val doActionAsInternalVal = fun ActionContext.(name: kotlin.String): Unit { println("Action ${type}.${name}")}
}

public data class ActionContext(val type: String)

