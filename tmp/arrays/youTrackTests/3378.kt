// Original bug: KT-19387

class TestPropertiesClash {
    /**
     * javadoc for getFooInternal
     * @return internal value of foo
     */
    /**
     * javadoc for foo
     */
    var fooInternal: String? = null

    /**
     * javadoc for getFoo
     * @return foo or an empty string
     */
    fun getFoo(): String {
        return if (fooInternal == null) "" else fooInternal!!
    }
}
