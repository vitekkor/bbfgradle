// Original bug: KT-4867

 fun setProp(
            currentValueGetter: () -> Int?,
            currentValueSetter: (Int?) -> Unit) {
 
          // getting value
          val oldValue = currentValueGetter();
 
           // setting value
          currentValueSetter(10);
 
    }
 