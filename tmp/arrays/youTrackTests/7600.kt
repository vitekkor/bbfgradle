// Original bug: KT-26466

package kui

abstract class Component

interface Renderer<C : Component> {
    val template: Template
}

class Template

inline fun <C : Component> buildTemplate(block: TemplateBuilder<C>.() -> Unit): Template {
    return BaseTemplateBuilder<C>().apply(block).build()
}

abstract class TemplateBuilder<C : Component> {
    inline fun div(block: TemplateElement<C>.() -> Unit) {
        println("hello")
        TemplateElement<C>().block()
    }
}

class BaseTemplateBuilder<C : Component> : TemplateBuilder<C>() {
    fun build(): Template {
        return Template()
    }
}

class TemplateElement<C : Component> : TemplateBuilder<C>() {
    inline fun <reified C2 : Component> component(renderer: Renderer<C2>) {
        println(C2::class.simpleName)
    }
}
