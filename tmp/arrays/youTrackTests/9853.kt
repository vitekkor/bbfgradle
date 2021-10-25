// Original bug: KT-10896

/* Tagged Error */

data class FormError(val context: String, val message: String)

/* Binding Result */

interface BindingResult<T>

data class SuccessBinding<T>(val value: T) : BindingResult<T>

data class FailedBinding<T>(val errors: List<FormError>) : BindingResult<T>

/* Form Binders */

interface FormBinder<T, V> {
    fun bind(data: T, context: String): BindingResult<V>

    fun unbind(data: V): T
}

interface ValidationBinder<T, V> : FormBinder<T, V> {
    fun verifying(validator: (V, String) -> List<FormError>): ValidationBinder<T, V> = object : ValidationBinder<T, V> {
        override fun bind(data: T, context: String): BindingResult<V> {
            val result = this@ValidationBinder.bind(data, context)

            return when (result) {
                is SuccessBinding -> {
                    val errors = validator(result.value, context)
                    // will throw error: java.lang.ClassCastException: jetprofile.shop.forms.FailedBinding cannot be cast to jetprofile.shop.forms.SuccessBinding
                    if (errors.isNotEmpty()) FailedBinding(errors) else result
                }
                else -> result
            }
        }

        override fun unbind(data: V): T = this@ValidationBinder.unbind(data)
    }

    fun verifying(message: String, predicate: (V) -> Boolean) =
            verifying { value, ctx -> if (predicate(value)) listOf() else listOf(FormError(ctx, message)) }
}
