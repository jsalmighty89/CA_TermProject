package game;

import org.newdawn.slick.Image;

import engine.base.AEVector;
import engine.framework.AEFramework;
import engine.object.AEGameObject;

public class GroundTile extends AEGameObject {
	
	protected AEGameObject[] objectTile;
	
	public GroundTile() {
		// create tiles
		int tileWidth = 8;
		int tileHeight = 8;
		int tileImageWidth = 0;
		int tileImageHeight = 0;
		for( int i=0; i<tileHeight; i++) {
			for( int j=0; j<tileWidth; j++) {
				AEGameObject tile = new AEGameObject();
				tile.setObjectName( "Ground Tile");
				tile.createSprite( "res/images/ground_tile.jpg");
				tile.getSprite().setDrawOrder( DrawOrder.GROUND.ordinal());
				Image image = tile.getSprite().getImage();
				tileImageWidth = image.getWidth();
				tileImageHeight = image.getHeight();
				tile.getTransform().setPosition( new AEVector( i * tileImageWidth, j * tileImageHeight));
				
				this.addChild( tile);
			}
		}		
		AEVector offset = new AEVector( -(tileImageWidth*tileWidth)*0.5f + tileImageWidth*0.5f,
										-(tileImageHeight*tileHeight)*0.5f + tileImageHeight*0.5f);
		this.getTransform().setPosition( offset);
	}
}
