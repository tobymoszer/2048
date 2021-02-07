package ty48;

import java.awt.*;

class Colors {
	
	private static final Color[] colors = {
			new Color(235,227,217),
			new Color(231,222,199),
			new Color(225,177,123),
			new Color(212,142,84),
			new Color(219,127,98),
			new Color(204,96,61),
			new Color(117,167,241),
			new Color(69,133,242),
			new Color(237,207,114),
			new Color(237,197,63),
			new Color(224,195,46),
			new Color(143,215,148),
			new Color(255,127,80),
			new Color(154,205,50),
			new Color(238,130,238),
			new Color(0, 0, 0),
			new Color(128,0,0),
			new Color(75,0,130),
			new Color(139,69,19),
			new Color(25,25,112),
			new Color(50, 50, 50),
			new Color(20, 70, 50)
	};
	
	static Color getColor(int value) {
		return colors[(int)(Math.log(value)/Math.log(2))];
	}
}
