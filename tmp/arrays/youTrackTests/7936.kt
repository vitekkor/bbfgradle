// Original bug: KT-23651

class TestTest {
    abstract class Point {
        abstract val x: Double
        abstract val y: Double
    }

    data class PointImpl(override val x: Double, override val y: Double) : Point()

    interface PointConsumer<T : Point> {
        var point: T
        fun consumePoint(point: T)
        fun transformPoint(point: T): Point
    }

    class PointConsumerImpl : PointConsumer<PointImpl> {
        override var point: PointImpl = PointImpl(1.0, 2.0)

        override fun consumePoint(point: PointImpl) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun transformPoint(point: PointImpl): Point {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
