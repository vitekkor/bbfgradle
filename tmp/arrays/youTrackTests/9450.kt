// Original bug: KT-9825

class Test {                                                                                                                           
  fun foo () {                                                                                                                         
    var exn :RuntimeException? = null                                                                                                  
    try {                                                                                                                              
      try {                                                                                                                            
        bar ()                                                                                                                         
      } catch (ex :RuntimeException) {                                                                                                 
        exn = ex                                                                                                                       
      }                                                                                                                                
    } finally {                                                                                                                        
      try {                                                                                                                            
        baz ()                                                                                                                         
      } catch (ex :RuntimeException) {                                                                                                 
        exn = ex                                                                                                                       
      }                                                                                                                                
    }                                                                                                                                  
    if (exn != null) throw exn                                                                                                         
  }                                                                                                                                    
                                                                                                                                       
  fun bar () {}                                                                                                                        
  fun baz () {}                                                                                                                        
}                                                                                                                                      
