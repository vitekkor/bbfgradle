// Original bug: KT-1302

fun foo(){
  var text : String = "";
  var maxLength = 0;

  fun replaceText(newText : String){
    if (text == newText) return
    text = newText
    print("\r")
    print(newText)
    val eraseCount = maxLength - newText.length
    if (eraseCount > 0){
      for(i in 1..eraseCount-1)
        print(" ")
      print("\t")
    }
    else{
      maxLength = newText.length
    }
  }

  while(true){
    val a = "a"
    val b = "b"
    replaceText(if (b != "") b else a)
  }
}
