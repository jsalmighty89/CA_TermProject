package game;

import org.newdawn.slick.Image;

import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;

public class GroundTile extends AEGameObject {
	
	protected AEGameObject[] objectTile;
	
	public GroundTile() {
		int tileWidth = 5;
		int tileHeight = 5;
		for( int i=0; i<tileHeight; i++) {
			for( int j=0; j<tileWidth; j++) {
				AEGameObject tile = new AEGameObject();
				tile.setObjectName( "Ground Tile");
				tile.createSprite( "res/images/ground_tile.jpg");
				tile.getSprite().setDrawOrder( DrawOrder.GROUND.ordinal());
				Image image = tile.getSprite().getImage();
				int width = image.getWidth();
				int height = image.getHeight();
				tile.getTransform().setPosition( new AEVector( i * width, j * height, 0.0f));
				
				this.addChild( tile);
			}
		}
	}
}
