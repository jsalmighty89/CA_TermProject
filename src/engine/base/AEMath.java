package engine.base;

public class AEMath {
	public static float deg2rad( float deg) {
		return deg * 0.01745329252f;
	}
	public static float rad2deg( float rad) {
		return rad * 57.295779513f;
	}
	
	public static float getRandomRange( float from, float to) {
		return (float)(Math.random() * ( to - from) + from);
	}
	
	public static float clamp( float value, float min, float max) {
		float returnValue = value;
		if( returnValue < min)
			returnValue = min;
		else if( returnValue > max)
			returnValue = max;		
		return returnValue;
	}
}
