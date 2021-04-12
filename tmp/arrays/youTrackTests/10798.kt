// Original bug: KT-914

import java.util.*

class Lifetime() {
 val attached = ArrayList< Function0<Unit> >()
   
 fun Attach(action : ()->Unit)
  {
    attached.add(action)
  }
  
  fun Dispose()
  {
    for(x in attached) x()
    attached.clear()
  }
}
