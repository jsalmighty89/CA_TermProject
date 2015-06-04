package engine.render;

import java.util.LinkedList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.base.AEMath;
import engine.object.AEObject;

public class AESprite extends AEObject {
	
	protected String fileName;
	protected Image image;
	protected int drawOrder;
	protected LinkedList<Image> listImage;
	protected int spriteIndex;
	
	public AESprite() {
		//this( "defaultImage.png");
	}
	public AESprite( String fileName) {
		listImage = new LinkedList<Image>();
		// TODO temp implementation for load image
		try {
			image = new Image( fileName);
			listImage.add( image);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		drawOrder = 0;
		spriteIndex = 0;
	}
	
	public void loadImage( String fileName) {
		try {
			Image image = new Image( fileName);
			listImage.add( image);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public String getFileName() {
		return fileName;
	}
	public Image getImage() {
		//return image;
		spriteIndex = AEMath.clamp( spriteIndex, 0, listImage.size());
		return listImage.get( spriteIndex);
	}
	public void setDrawOrder( int drawOrder) {
		this.drawOrder = drawOrder;
	}
	public int getDrawOrder() {
		return drawOrder;
	}
	public void setSpriteIndex( int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
	public int getSpriteIndex() {
		return spriteIndex;
	}
}
