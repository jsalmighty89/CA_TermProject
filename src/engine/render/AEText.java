package engine.render;

import org.newdawn.slick.Font;

import engine.object.AEObject;

public class AEText extends AEObject {
	protected String font;
	protected String text;
	
	public AEText() {
		
	}
	public AEText( String font, String text) {
		this.font = font;
		this.text = text;
	}
	public void setFont( String font) {
		this.font = font;
	}
	public String getFont() {
		return font;
	}
	public void setText( String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
}
