package game;

import engine.framework.AEFramework;
import engine.object.AEGameObject;

public class GroundTile extends AEGameObject {
	
	protected AEGameObject[] objectTile;
	
	public GroundTile() {
		objectTile = new AEGameObject[1];
		for( int i=0; i<objectTile.length; i++) {
			objectTile[i] = new AEGameObject();
			objectTile[i].setObjectName( "Ground Tile");
			objectTile[i].createSprite( "res/images/ground_tile.jpg");
			objectTile[i].getSprite().setDrawOrder( DrawOrder.GROUND.ordinal());
			
			this.addChild( objectTile[i]);
		}
	}
}
