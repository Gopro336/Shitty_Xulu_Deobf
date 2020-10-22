package org.newdawn.slick.imageout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageOut {
   // $FF: synthetic field
   public static String JPG = "jpg";
   // $FF: synthetic field
   public static String PNG = "png";
   // $FF: synthetic field
   private static final boolean DEFAULT_ALPHA_WRITE = false;
   // $FF: synthetic field
   public static String TGA = "tga";

   public static String[] getSupportedFormats() {
      return ImageWriterFactory.getSupportedFormats();
   }

   public static void write(Image var0, String var1, String var2, boolean var3) throws SlickException {
      try {
         write(var0, var1, (OutputStream)(new FileOutputStream(var2)), var3);
      } catch (IOException var5) {
         throw new SlickException(String.valueOf((new StringBuilder()).append("Unable to write to the destination: ").append(var2)), var5);
      }
   }

   public static void write(Image var0, String var1, OutputStream var2) throws SlickException {
      write(var0, var1, var2, false);
   }

   public static void write(Image var0, String var1, boolean var2) throws SlickException {
      try {
         int var3 = var1.lastIndexOf(46);
         if (var3 < 0) {
            throw new SlickException(String.valueOf((new StringBuilder()).append("Unable to determine format from: ").append(var1)));
         } else {
            String var4 = var1.substring(var3 + 1);
            write(var0, var4, (OutputStream)(new FileOutputStream(var1)), var2);
         }
      } catch (IOException var5) {
         throw new SlickException(String.valueOf((new StringBuilder()).append("Unable to write to the destination: ").append(var1)), var5);
      }
   }

   public static void write(Image var0, String var1, OutputStream var2, boolean var3) throws SlickException {
      try {
         ImageWriter var4 = ImageWriterFactory.getWriterForFormat(var1);
         var4.saveImage(var0, var1, var2, var3);
      } catch (IOException var5) {
         throw new SlickException(String.valueOf((new StringBuilder()).append("Unable to write out the image in format: ").append(var1)), var5);
      }
   }

   public static void write(Image var0, String var1) throws SlickException {
      write(var0, var1, false);
   }

   public static void write(Image var0, String var1, String var2) throws SlickException {
      write(var0, var1, var2, false);
   }
}
