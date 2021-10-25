// Original bug: KT-9961

class SubscriptionProduct private constructor(val sku: String) {
    companion object {
        @JvmField
        val PDF_1Y = SubscriptionProduct("pdf_1y")
    }
}
