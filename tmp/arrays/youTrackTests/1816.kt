// Original bug: KT-10349

fun indexOfMax(a: IntArray): Int? {
    if(a.size==0) return null;
    var idx=0    
    for(i in 0..(a.size-1)){
        if(a[i]>a[idx]){
            idx=i            
        }
    }
    return idx
}
