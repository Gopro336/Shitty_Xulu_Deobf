package org.newdawn.slick;

import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class CachedRender {
   // $FF: synthetic field
   private int list = -1;
   // $FF: synthetic field
   protected static SGL GL = Renderer.get();
   // $FF: synthetic field
   private Runnable runnable;

   public void destroy() {
      GL.glDeleteLists(this.list, 1);
      this.list = -1;
   }

   private void build() {
      if (this.list == -1) {
         this.list = GL.glGenLists(1);
         SlickCallable.enterSafeBlock();
         GL.glNewList(this.list, 4864);
         this.runnable.run();
         GL.glEndList();
         SlickCallable.leaveSafeBlock();
      } else {
         throw new RuntimeException("Attempt to build the display list more than once in CachedRender");
      }
   }

   public void render() {
      if (this.list == -1) {
         throw new RuntimeException("Attempt to render cached operations that have been destroyed");
      } else {
         SlickCallable.enterSafeBlock();
         GL.glCallList(this.list);
         SlickCallable.leaveSafeBlock();
      }
   }

   public CachedRender(Runnable var1) {
      this.runnable = var1;
      this.build();
   }
}
