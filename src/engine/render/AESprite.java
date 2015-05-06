package engine.render;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.object.AEObject;

public class AESprite extends AEObject {
	
	protected String fileName;
	protected Image image;
	
	public AESprite() {
		this( "defaultImage.png");
	}
	public AESprite( String fileName) {
		// TODO temp implementation for load image
		try {
			image = new Image( fileName);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public String getFileName() {
		return fileName;
	}
	public Image getImage() {
		return image;
	}
}
