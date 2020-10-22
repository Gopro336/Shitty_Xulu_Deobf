package org.newdawn.slick.particles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class Particle {
   // $FF: synthetic field
   protected float vely;
   // $FF: synthetic field
   private ParticleSystem engine;
   // $FF: synthetic field
   protected float x;
   // $FF: synthetic field
   protected int type;
   // $FF: synthetic field
   protected float originalLife;
   // $FF: synthetic field
   private ParticleEmitter emitter;
   // $FF: synthetic field
   protected boolean oriented;
   // $FF: synthetic field
   protected static SGL GL = Renderer.get();
   // $FF: synthetic field
   protected Image image;
   // $FF: synthetic field
   protected float y;
   // $FF: synthetic field
   protected float scaleY;
   // $FF: synthetic field
   protected float life;
   // $FF: synthetic field
   protected Color color;
   // $FF: synthetic field
   public static final int INHERIT_POINTS = 1;
   // $FF: synthetic field
   protected float velx;
   // $FF: synthetic field
   protected int usePoints;
   // $FF: synthetic field
   public static final int USE_POINTS = 2;
   // $FF: synthetic field
   public static final int USE_QUADS = 3;
   // $FF: synthetic field
   protected float size = 10.0F;

   public void setVelocity(float var1, float var2, float var3) {
      this.velx = var1 * var3;
      this.vely = var2 * var3;
   }

   public int getType() {
      return this.type;
   }

   public ParticleEmitter getEmitter() {
      return this.emitter;
   }

   public Particle(ParticleSystem var1) {
      this.color = Color.white;
      this.usePoints = 1;
      this.oriented = false;
      this.scaleY = 1.0F;
      this.engine = var1;
   }

   public void setSpeed(float var1) {
      float var2 = (float)Math.sqrt((double)(this.velx * this.velx + this.vely * this.vely));
      this.velx *= var1;
      this.vely *= var1;
      this.velx /= var2;
      this.vely /= var2;
   }

   public void setImage(Image var1) {
      this.image = var1;
   }

   public float getSize() {
      return this.size;
   }

   public void setScaleY(float var1) {
      this.scaleY = var1;
   }

   public void setPosition(float var1, float var2) {
      this.x = var1;
      this.y = var2;
   }

   public void setLife(float var1) {
      this.life = var1;
   }

   public void adjustVelocity(float var1, float var2) {
      this.velx += var1;
      this.vely += var2;
   }

   public void update(int var1) {
      this.emitter.updateParticle(this, var1);
      this.life -= (float)var1;
      if (this.life > 0.0F) {
         this.x += (float)var1 * this.velx;
         this.y += (float)var1 * this.vely;
      } else {
         this.engine.release(this);
      }

   }

   public void setSize(float var1) {
      this.size = var1;
   }

   public void adjustLife(float var1) {
      this.life += var1;
   }

   public void setUsePoint(int var1) {
      this.usePoints = var1;
   }

   public void setVelocity(float var1, float var2) {
      this.setVelocity(var1, var2, 1.0F);
   }

   public void adjustColor(float var1, float var2, float var3, float var4) {
      if (this.color == Color.white) {
         this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
      }

      Color var10000 = this.color;
      var10000.r += var1;
      var10000 = this.color;
      var10000.g += var2;
      var10000 = this.color;
      var10000.b += var3;
      var10000 = this.color;
      var10000.a += var4;
   }

   public float getLife() {
      return this.life;
   }

   public void adjustSize(float var1) {
      this.size += var1;
      this.size = Math.max(0.0F, this.size);
   }

   public void adjustColor(int var1, int var2, int var3, int var4) {
      if (this.color == Color.white) {
         this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
      }

      Color var10000 = this.color;
      var10000.r += (float)var1 / 255.0F;
      var10000 = this.color;
      var10000.g += (float)var2 / 255.0F;
      var10000 = this.color;
      var10000.b += (float)var3 / 255.0F;
      var10000 = this.color;
      var10000.a += (float)var4 / 255.0F;
   }

   public void render() {
      if ((!this.engine.usePoints() || this.usePoints != 1) && this.usePoints != 2) {
         if (!this.oriented && this.scaleY == 1.0F) {
            this.color.bind();
            this.image.drawEmbedded((float)((int)(this.x - this.size / 2.0F)), (float)((int)(this.y - this.size / 2.0F)), (float)((int)this.size), (float)((int)this.size));
         } else {
            GL.glPushMatrix();
            GL.glTranslatef(this.x, this.y, 0.0F);
            if (this.oriented) {
               float var1 = (float)(Math.atan2((double)this.y, (double)this.x) * 180.0D / 3.141592653589793D);
               GL.glRotatef(var1, 0.0F, 0.0F, 1.0F);
            }

            GL.glScalef(1.0F, this.scaleY, 1.0F);
            this.image.draw((float)((int)(-(this.size / 2.0F))), (float)((int)(-(this.size / 2.0F))), (float)((int)this.size), (float)((int)this.size), this.color);
            GL.glPopMatrix();
         }
      } else {
         TextureImpl.bindNone();
         GL.glEnable(2832);
         GL.glPointSize(this.size / 2.0F);
         this.color.bind();
         GL.glBegin(0);
         GL.glVertex2f(this.x, this.y);
         GL.glEnd();
      }

   }

   public float getX() {
      return this.x;
   }

   public Color getColor() {
      return this.color;
   }

   public boolean isOriented() {
      return this.oriented;
   }

   public float getScaleY() {
      return this.scaleY;
   }

   public float getY() {
      return this.y;
   }

   public void init(ParticleEmitter var1, float var2) {
      this.x = 0.0F;
      this.emitter = var1;
      this.y = 0.0F;
      this.velx = 0.0F;
      this.vely = 0.0F;
      this.size = 10.0F;
      this.type = 0;
      this.originalLife = this.life = var2;
      this.oriented = false;
      this.scaleY = 1.0F;
   }

   public void adjustPosition(float var1, float var2) {
      this.x += var1;
      this.y += var2;
   }

   public void setOriented(boolean var1) {
      this.oriented = var1;
   }

   public void setColor(float var1, float var2, float var3, float var4) {
      if (this.color == Color.white) {
         this.color = new Color(var1, var2, var3, var4);
      } else {
         this.color.r = var1;
         this.color.g = var2;
         this.color.b = var3;
         this.color.a = var4;
      }

   }

   public void kill() {
      this.life = 1.0F;
   }

   public float getOriginalLife() {
      return this.originalLife;
   }

   public boolean inUse() {
      return this.life > 0.0F;
   }

   public void move(float var1, float var2) {
      this.x += var1;
      this.y += var2;
   }

   public String toString() {
      return String.valueOf((new StringBuilder()).append(super.toString()).append(" : ").append(this.life));
   }

   public void setType(int var1) {
      this.type = var1;
   }
}
