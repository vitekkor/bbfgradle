// Original bug: KT-15726

import java.lang.Exception

fun nyCompiler() {
    try {
        return
    }
    catch (e: Exception) {}
    finally {
        try {}  
        catch (e: Exception) {}
    }
}
