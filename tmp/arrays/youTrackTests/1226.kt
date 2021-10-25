// Original bug: KT-33634

    interface BaseKSupplier<T> {
        fun get(): T;
    }
    interface SpecialKSupplier : BaseKSupplier<String> {
        override fun get(): String;
    }
