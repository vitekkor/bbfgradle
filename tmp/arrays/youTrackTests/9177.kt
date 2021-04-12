// Original bug: KT-15201

fun theFunction(f: java.util.Map<Int, java.nio.file.AccessMode>) {
    val e = f.get(0)
    if( e == null ) return
    when(e) {
        java.nio.file.AccessMode.EXECUTE -> return
        java.nio.file.AccessMode.READ -> return
        java.nio.file.AccessMode.WRITE -> return
        //null -> return //This should not be needed to avoid warning.
    }    
}
