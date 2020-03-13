//File A.java
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

public abstract class A implements Collection, KMappedMarker {
   @NotNull
   protected final Object[] foo(@NotNull Object[] x) {
      return x;
   }

   public abstract int getSize();

   public abstract boolean contains(String var1);

   public Iterator iterator() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean add(String var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean addAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public void clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean remove(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean removeAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean removeIf(Predicate var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean retainAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   // $FF: synthetic method
   public boolean add(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public Object[] toArray() {
      return CollectionToArray.toArray(this);
   }

   public Object[] toArray(Object[] var1) {
      return CollectionToArray.toArray(this, var1);
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// SKIP_JDK6
// TARGET_BACKEND: JVM
// FULL_JDK
// WITH_RUNTIME

import java.lang.reflect.Modifier

fun box(): String {
    val method = A::class.java.declaredMethods.single { it.name == "foo" }
    return if (Modifier.isProtected(method.modifiers)) "OK" else "Fail: $method"
}

