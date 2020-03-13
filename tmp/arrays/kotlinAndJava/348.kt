//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

public final class A {
   private static int xi;
   @Nullable
   private static Integer xin = 0;
   @Nullable
   private static Integer xinn;
   private static long xl;
   @Nullable
   private static Long xln = 0L;
   @Nullable
   private static Long xlnn;
   private static byte xb;
   @Nullable
   private static Byte xbn = (byte)0;
   @Nullable
   private static Byte xbnn;
   private static float xf;
   @Nullable
   private static Float xfn = (float)0;
   @Nullable
   private static Float xfnn;
   private static double xd;
   @Nullable
   private static Double xdn = (double)0;
   @Nullable
   private static Double xdnn;
   private static short xs;
   @Nullable
   private static Short xsn = (short)0;
   @Nullable
   private static Short xsnn;
   public static final A.Companion Companion = new A.Companion((DefaultConstructorMarker)null);

   public static final class Companion {
      public final int getXi() {
         return A.xi;
      }

      public final void setXi(int var1) {
         A.xi = var1;
      }

      @Nullable
      public final Integer getXin() {
         return A.xin;
      }

      public final void setXin(@Nullable Integer var1) {
         A.xin = var1;
      }

      @Nullable
      public final Integer getXinn() {
         return A.xinn;
      }

      public final void setXinn(@Nullable Integer var1) {
         A.xinn = var1;
      }

      public final long getXl() {
         return A.xl;
      }

      public final void setXl(long var1) {
         A.xl = var1;
      }

      @Nullable
      public final Long getXln() {
         return A.xln;
      }

      public final void setXln(@Nullable Long var1) {
         A.xln = var1;
      }

      @Nullable
      public final Long getXlnn() {
         return A.xlnn;
      }

      public final void setXlnn(@Nullable Long var1) {
         A.xlnn = var1;
      }

      public final byte getXb() {
         return A.xb;
      }

      public final void setXb(byte var1) {
         A.xb = var1;
      }

      @Nullable
      public final Byte getXbn() {
         return A.xbn;
      }

      public final void setXbn(@Nullable Byte var1) {
         A.xbn = var1;
      }

      @Nullable
      public final Byte getXbnn() {
         return A.xbnn;
      }

      public final void setXbnn(@Nullable Byte var1) {
         A.xbnn = var1;
      }

      public final float getXf() {
         return A.xf;
      }

      public final void setXf(float var1) {
         A.xf = var1;
      }

      @Nullable
      public final Float getXfn() {
         return A.xfn;
      }

      public final void setXfn(@Nullable Float var1) {
         A.xfn = var1;
      }

      @Nullable
      public final Float getXfnn() {
         return A.xfnn;
      }

      public final void setXfnn(@Nullable Float var1) {
         A.xfnn = var1;
      }

      public final double getXd() {
         return A.xd;
      }

      public final void setXd(double var1) {
         A.xd = var1;
      }

      @Nullable
      public final Double getXdn() {
         return A.xdn;
      }

      public final void setXdn(@Nullable Double var1) {
         A.xdn = var1;
      }

      @Nullable
      public final Double getXdnn() {
         return A.xdnn;
      }

      public final void setXdnn(@Nullable Double var1) {
         A.xdnn = var1;
      }

      public final short getXs() {
         return A.xs;
      }

      public final void setXs(short var1) {
         A.xs = var1;
      }

      @Nullable
      public final Short getXsn() {
         return A.xsn;
      }

      public final void setXsn(@Nullable Short var1) {
         A.xsn = var1;
      }

      @Nullable
      public final Short getXsnn() {
         return A.xsnn;
      }

      public final void setXsnn(@Nullable Short var1) {
         A.xsnn = var1;
      }

      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}


//File Main.kt


fun box() : String {
    return "OK"
}

