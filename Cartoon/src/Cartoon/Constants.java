package Cartoon;

/*
 * This class contains the values for the initial sizes and positions
 * of the objects in the game. It serves as a central place for all
 * the constants so that these variables can be called in the
 * other classes rather than the numbers themselves.
 */
public class Constants {
	
    public static final double GAMEPANE_PREF_WIDTH = 700;
    public static final double GAMEPANE_PREF_HEIGHT = 600;

    public static final double START_HEIGHT = GAMEPANE_PREF_HEIGHT; 
    
    public static final double TRI_WIDTH = 70;
    public static final double TRI_HEIGHT = 40;
    public static final double TRI_X = GAMEPANE_PREF_WIDTH/2;

    public static final double REC_WIDTH = 40;
    public static final double REC_HEIGHT = 70;
    public static final double REC_X = TRI_X + (TRI_WIDTH-REC_WIDTH)/2;

    public static final double FUSE_X = REC_X + (REC_WIDTH)/2;  
    
    public static final double SIZ_LABEL_X = FUSE_X +8;  

    public static final double FIREWORK_WIDTH= 400;
}
