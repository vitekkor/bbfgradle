//File TextField.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface TextField {
   @NotNull
   String getText();

   void setText(@NotNull String var1);
}


//File SimpleTextField.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class SimpleTextField implements TextField {
   private String text2 = "";

   @NotNull
   public String getText() {
      return this.text2;
   }

   public void setText(@NotNull String text) {
      this.text2 = text;
   }
}


//File TextFieldWrapper.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class TextFieldWrapper implements TextField {
   // $FF: synthetic field
   private final TextField $$delegate_0;

   public TextFieldWrapper(@NotNull TextField textField) {
      super();
      this.$$delegate_0 = textField;
   }

   @NotNull
   public String getText() {
      return this.$$delegate_0.getText();
   }

   public void setText(@NotNull String text) {
      this.$$delegate_0.setText(text);
   }
}


//File Main.kt


fun box() : String {
    val textField = TextFieldWrapper(SimpleTextField())
    textField.setText("OK")
    return textField.getText()
}

