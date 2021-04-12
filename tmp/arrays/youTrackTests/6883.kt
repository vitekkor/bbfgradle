// Original bug: KT-27807

private const val CARD_PAYMENT_METHOD = "CARD"

data class ServiceQuote(val alternativeFares: Map<String, AlternativeFare>)

data class AlternativeFare(
    val uxType: CharSequence? = "foo",
    val paymentMethodID: Id = Id(),
    val discount: Discount?,
    val discountEligibilityError: WrongPayment,
    val uxMessage: String?
)

class Id {
    fun isPaymentSupported(supportedPaymentMethod: Set<String>?): Boolean {
        return true
    }
}

fun ServiceQuote.fillWithPaymentDiscount(supportedPaymentMethod: Set<String>?): ServiceQuote {
    val (discount, message) = this.discountGPP() ?: return this
    val filledDiscountAlternativeFares = alternativeFares.mapValues { entry ->
        val fare = entry.value
        if (fare.uxType.isNullOrEmpty() && fare.paymentMethodID.isPaymentSupported(
                supportedPaymentMethod
            )
            && supportedPaymentMethod?.contains(CARD_PAYMENT_METHOD) == true
        ) {
            return@mapValues fare.copy(
                uxType = PRE_DISCOUNT_GPP,
                discount = discount,
                discountEligibilityError = DISCOUNT_ERROR_WRONG_PAYMENT.copy(
                    localizedMessage = message ?: ""
                )
            )
        }
        return@mapValues fare
    }
    return this.copy(alternativeFares = filledDiscountAlternativeFares)
}

val DISCOUNT_ERROR_WRONG_PAYMENT: WrongPayment = WrongPayment("")

data class WrongPayment(val localizedMessage: String)

val PRE_DISCOUNT_GPP: CharSequence = "GPP"
val PRE_DISCOUNT_GP: CharSequence = "GP"

private data class DiscountMessagePair(val discount: Discount?, val message: String?)

class Discount

private fun ServiceQuote.discountGPP(): DiscountMessagePair? =
    this.alternativeFares.entries.firstOrNull { it.value.uxType == PRE_DISCOUNT_GPP || it.value.uxType == PRE_DISCOUNT_GP }
        ?.let { DiscountMessagePair(it.value.discount, it.value.uxMessage) }
