//File HttpServer.java
import kotlin.Metadata;

public abstract class HttpServer implements LoggerAware {
   public final void start() {
      this.getLogger().append("OK");
   }
}


//File MyHttpServer.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class MyHttpServer extends HttpServer {
   @NotNull
   private final StringBuilder logger = new StringBuilder();

   @NotNull
   public StringBuilder getLogger() {
      return this.logger;
   }
}


//File Main.kt


fun box(): String {
    val server = MyHttpServer()
    server.start()
    return server.logger.toString()!!
}



//File LoggerAware.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface LoggerAware {
   @NotNull
   StringBuilder getLogger();
}
