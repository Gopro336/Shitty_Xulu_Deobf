package org.newdawn.slick.opengl;

import java.io.IOException;
import java.io.InputStream;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

public class DeferredTexture extends TextureImpl implements DeferredResource {
   // $FF: synthetic field
   private int[] trans;
   // $FF: synthetic field
   private InputStream in;
   // $FF: synthetic field
   private String resourceName;
   // $FF: synthetic field
   private TextureImpl target;
   // $FF: synthetic field
   private boolean flipped;
   // $FF: synthetic field
   private int filter;

   public int getImageHeight() {
      this.checkTarget();
      return this.target.getImageHeight();
   }

   public void setTextureID(int var1) {
      this.checkTarget();
      this.target.setTextureID(var1);
   }

   public void setWidth(int var1) {
      this.checkTarget();
      this.target.setWidth(var1);
   }

   public void setHeight(int var1) {
      this.checkTarget();
      this.target.setHeight(var1);
   }

   public void setAlpha(boolean var1) {
      this.checkTarget();
      this.target.setAlpha(var1);
   }

   public String getTextureRef() {
      this.checkTarget();
      return this.target.getTextureRef();
   }

   public int getImageWidth() {
      this.checkTarget();
      return this.target.getImageWidth();
   }

   public byte[] getTextureData() {
      this.checkTarget();
      return this.target.getTextureData();
   }

   public String getDescription() {
      return this.resourceName;
   }

   public void setTextureHeight(int var1) {
      this.checkTarget();
      this.target.setTextureHeight(var1);
   }

   public DeferredTexture(InputStream var1, String var2, boolean var3, int var4, int[] var5) {
      this.in = var1;
      this.resourceName = var2;
      this.flipped = var3;
      this.filter = var4;
      this.trans = var5;
      LoadingList.get().add(this);
   }

   public void setTextureWidth(int var1) {
      this.checkTarget();
      this.target.setTextureWidth(var1);
   }

   public void release() {
      this.checkTarget();
      this.target.release();
   }

   public int getTextureID() {
      this.checkTarget();
      return this.target.getTextureID();
   }

   public boolean hasAlpha() {
      this.checkTarget();
      return this.target.hasAlpha();
   }

   public int getTextureWidth() {
      this.checkTarget();
      return this.target.getTextureWidth();
   }

   public float getWidth() {
      this.checkTarget();
      return this.target.getWidth();
   }

   public float getHeight() {
      this.checkTarget();
      return this.target.getHeight();
   }

   public void setTextureFilter(int var1) {
      this.checkTarget();
      this.target.setTextureFilter(var1);
   }

   private void checkTarget() {
      if (this.target == null) {
         try {
            this.load();
            LoadingList.get().remove(this);
         } catch (IOException var2) {
            throw new RuntimeException(String.valueOf((new StringBuilder()).append("Attempt to use deferred texture before loading and resource not found: ").append(this.resourceName)));
         }
      }
   }

   public void load() throws IOException {
      boolean var1 = InternalTextureLoader.get().isDeferredLoading();
      InternalTextureLoader.get().setDeferredLoading(false);
      this.target = InternalTextureLoader.get().getTexture(this.in, this.resourceName, this.flipped, this.filter, this.trans);
      InternalTextureLoader.get().setDeferredLoading(var1);
   }

   public void bind() {
      this.checkTarget();
      this.target.bind();
   }

   public int getTextureHeight() {
      this.checkTarget();
      return this.target.getTextureHeight();
   }
}
