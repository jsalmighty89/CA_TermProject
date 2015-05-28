package engine.render;

import java.awt.Font;
import java.util.HashMap;

import org.newdawn.slick.TrueTypeFont;

import engine.object.AEObject;

public class AEFontTable extends AEObject {
	protected HashMap<String, TrueTypeFont> mapFont;
	
	public AEFontTable() {
		mapFont = new HashMap<String, TrueTypeFont>();
		
		createFont( "Default", 32, false);		
	}
	
	public void createFont( String name, int size, boolean bold) {
		Font awtFont = new Font( "Tahoma", (bold?Font.BOLD:Font.PLAIN), size);
		TrueTypeFont font = new TrueTypeFont( awtFont, true);
		if( mapFont.containsKey( name) == false)
			mapFont.put( name, font);
		else
			System.out.printf( "[WARN] Font %s is duplicated.\n", name);
	}
	
	public TrueTypeFont getFont( String name) {
		if( mapFont.containsKey( name))
			return mapFont.get( name);
		else
			return mapFont.get( "Default");
	}
}
