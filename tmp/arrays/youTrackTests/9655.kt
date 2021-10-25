// Original bug: KT-11568


import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

var COUNT: Int = 1000;

fun main(args: Array<String>) {
// Comment the while loop and uncomment the for loop to run the for test
    whileLoop();
//    forLoop();
}

fun whileLoop() {
    val mxBean: ThreadMXBean = ManagementFactory.getThreadMXBean()
    var start: Long;
    var i: Int;
    var j: Int;
    var k: Int;

    for (x in 1..1000) {
        start = mxBean.currentThreadCpuTime;

        i = 0;
        while (i < COUNT) {
            j = 0;
            while (j < COUNT) {
                k = 0;
                while (k < COUNT) {
                    ++k;
                }
                ++j;
            }
            ++i;
        }

        println(mxBean.currentThreadCpuTime - start);
    }
}

fun forLoop() {
    val mxBean: ThreadMXBean = ManagementFactory.getThreadMXBean()
    var start: Long;

    for (x in 1..1000) {
        start = mxBean.currentThreadCpuTime;

        for (i in 1..COUNT) {
            for (j in 1..COUNT) {
                for (k in 1..COUNT) {
                    // do nothing
                }
            }
        }

        println(mxBean.currentThreadCpuTime - start);
    }
}

