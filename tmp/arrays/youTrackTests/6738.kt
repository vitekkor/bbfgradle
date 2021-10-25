// Original bug: KT-16285

    val a0 = 0
    val a1 = arrayOf(a0) //Compilation completed successfully in 1s 470ms
    val a2 = arrayOf(a1) //Compilation completed successfully in 1s 441ms
    val a3 = arrayOf(a2) //Compilation completed successfully in 1s 474ms
    val a4 = arrayOf(a3) //Compilation completed successfully in 1s 492ms
    val a5 = arrayOf(a4) //Compilation completed successfully in 1s 497ms
    val a6 = arrayOf(a5) //Compilation completed successfully in 1s 463ms
    val a7 = arrayOf(a6) //Compilation completed successfully in 1s 429ms
    val a8 = arrayOf(a7) //Compilation completed successfully in 1s 491ms
    val a9 = arrayOf(a8) //Compilation completed successfully in 1s 482ms
    val a10 = arrayOf(a9) //Compilation completed successfully in 1s 504ms
    val a11 = arrayOf(a10) //Compilation completed successfully in 1s 527ms
    val a12 = arrayOf(a11) //Compilation completed successfully in 1s 490ms
    val a13 = arrayOf(a12) //Compilation completed successfully in 1s 478ms
    val a14 = arrayOf(a13) //Compilation completed successfully in 1s 553ms
    val a15 = arrayOf(a14) //Compilation completed successfully in 1s 685ms
    val a16 = arrayOf(a15) //Compilation completed successfully in 1s 953ms
    val a17 = arrayOf(a16) //Compilation completed successfully in 2s 336ms
    val a18 = arrayOf(a17) //Compilation completed successfully in 3s 359ms
    val a19 = arrayOf(a18) //Compilation completed successfully in 5s 375ms
    val a20 = arrayOf(a19) //Compilation completed successfully in 9s 695ms
    val a21 = arrayOf(a20) //Compilation completed successfully in 19s 553ms
    val a22 = arrayOf(a21) //Compilation completed successfully in 36s 208ms
    val a23 = arrayOf(a22) //Compilation completed successfully in 1m 18s 215ms
