package engine.object;

import engine.render.AEText;

public class AEUIObject extends AEGameObject {
	
	protected AEText textComponent;
	
	public AEUIObject() {
		
	}
	
	public boolean hasText() {
		return (textComponent != null);
	}
	public void createText( String font, String text) {
		textComponent = new AEText( font, text);
	}
	public AEText getText() {
		return textComponent;
	}
	
}
