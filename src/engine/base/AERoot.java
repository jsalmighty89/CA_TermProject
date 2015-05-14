package engine.base;

public class AERoot {
	public AERoot() {
		
	}
	
	public boolean isTypeOf( Class cls) {
		Class currentClass = this.getClass();
		while( currentClass != Object.class) {
			if( currentClass == cls)
				return true;
			currentClass = currentClass.getSuperclass();
		}
		
		return false;
	}
}