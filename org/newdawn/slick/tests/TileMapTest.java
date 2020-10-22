package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class TileMapTest extends BasicGame {
   // $FF: synthetic field
   private String monsterDifficulty;
   // $FF: synthetic field
   private String nonExistingLayerProperty;
   // $FF: synthetic field
   private TiledMap map;
   // $FF: synthetic field
   private int originalTileID = 0;
   // $FF: synthetic field
   private String nonExistingMapProperty;
   // $FF: synthetic field
   private int updateCounter = 0;
   // $FF: synthetic field
   private static int UPDATE_TIME = 1000;
   // $FF: synthetic field
   private String mapName;

   public void keyPressed(int var1, char var2) {
      if (var1 == 1) {
         System.exit(0);
      }

   }

   public TileMapTest() {
      super("Tile Map Test");
   }

   public void update(GameContainer var1, int var2) {
      this.updateCounter += var2;
      if (this.updateCounter > UPDATE_TIME) {
         this.updateCounter -= UPDATE_TIME;
         int var3 = this.map.getTileId(10, 10, 0);
         if (var3 != this.originalTileID) {
            this.map.setTileId(10, 10, 0, this.originalTileID);
         } else {
            this.map.setTileId(10, 10, 0, 1);
         }
      }

   }

   public void render(GameContainer var1, Graphics var2) {
      this.map.render(10, 10, 4, 4, 15, 15);
      var2.scale(0.35F, 0.35F);
      this.map.render(1400, 0);
      var2.resetTransform();
      var2.drawString(String.valueOf((new StringBuilder()).append("map name: ").append(this.mapName)), 10.0F, 500.0F);
      var2.drawString(String.valueOf((new StringBuilder()).append("monster difficulty: ").append(this.monsterDifficulty)), 10.0F, 550.0F);
      var2.drawString(String.valueOf((new StringBuilder()).append("non existing map property: ").append(this.nonExistingMapProperty)), 10.0F, 525.0F);
      var2.drawString(String.valueOf((new StringBuilder()).append("non existing layer property: ").append(this.nonExistingLayerProperty)), 10.0F, 575.0F);
   }

   public void init(GameContainer var1) throws SlickException {
      this.map = new TiledMap("testdata/testmap.tmx", "testdata");
      this.mapName = this.map.getMapProperty("name", "Unknown map name");
      this.monsterDifficulty = this.map.getLayerProperty(0, "monsters", "easy peasy");
      this.nonExistingMapProperty = this.map.getMapProperty("zaphod", "Undefined map property");
      this.nonExistingLayerProperty = this.map.getLayerProperty(1, "beeblebrox", "Undefined layer property");
      this.originalTileID = this.map.getTileId(10, 10, 0);
   }

   public static void main(String[] var0) {
      try {
         AppGameContainer var1 = new AppGameContainer(new TileMapTest());
         var1.setDisplayMode(800, 600, false);
         var1.start();
      } catch (SlickException var2) {
         var2.printStackTrace();
      }

   }
}
