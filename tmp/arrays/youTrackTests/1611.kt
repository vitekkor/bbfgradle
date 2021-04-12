// Original bug: KT-38967

import java.math.BigDecimal

interface Asset<This : Asset<This>> {
    val quantity: BigDecimal

    fun toDollar(): DollarAsset
    fun toRuble(): RubleAsset

    fun copy(quantity: BigDecimal): This
}

abstract class BaseAsset<This : BaseAsset<This>>(quantity: BigDecimal) : Asset<This> {
    final override val quantity: BigDecimal

    init {
        require(quantity >= BigDecimal.ZERO) { "$quantity is negative" }
        this.quantity = quantity.setScale(10)
    }

    @Suppress("UNCHECKED_CAST")
    final override fun copy(quantity: BigDecimal): This {
        return (javaClass.constructors
            .single { it.parameterCount == 1 && it.parameters.single().type == BigDecimal::class.java }
            ?.newInstance(quantity)
            ?: error("Every asset must have one constructor which takes one ${BigDecimal::class.java} parameter")) as This
    }
}

abstract class BaseCurrencyAsset<This : BaseCurrencyAsset<This>>(quantity: BigDecimal) : BaseAsset<This>(quantity) {

    operator fun plus(other: This): This = copy(quantity + other.quantity)

    operator fun minus(other: This): This = copy(quantity - other.quantity)

    override fun toString(): String = "${javaClass.simpleName}($quantity)"
}

class DollarAsset(quantity: BigDecimal) : BaseCurrencyAsset<DollarAsset>(quantity) {
    override fun toDollar(): DollarAsset = this

    override fun toRuble(): RubleAsset {
        TODO("Not yet implemented")
    }
}

class RubleAsset(quantity: BigDecimal) : BaseCurrencyAsset<RubleAsset>(quantity) {
    override fun toDollar(): DollarAsset {
        TODO("Not yet implemented")
    }

    override fun toRuble(): RubleAsset = this
}
