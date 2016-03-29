package main;
import javafx.geometry.Point3D;


public class Utils {
	
	static boolean DEBUG = true;
	
	/**
	 * @param s - Scheitelpunkt
	 * @param p1 - Punkt zum 1. Schenkel
	 * @param p2 - Punkt zum 2. Schenkel
	 * @return Winkel in Grad
	 */
	public static double getAngle(Point3D s, Point3D p1, Point3D p2) {
		
		/**
		 *                v1 * v2
		 *  cos(a) = ----------------
		 * 				|v1| * |v2|
		 */
		
		double angle = 0.0;
		
		Point3D v1 = getVector(s, p1);
		Point3D v2 = getVector(s, p2);
		
		double value = getScalar(v1, v2) / (getLength(v1) * getLength(v2));
		
		// cos^-1(value) berechnen
		double radian = Math.acos(value);
		angle = Math.toDegrees(radian);
		
		return angle;
	}
	
	/**
	 * @param p1 - Punkt 1
	 * @param p2 - Punkt 2
	 * @return Vektor vom Punkt <i>p1</i> zum Punkt <i>p2</i>.
	 */
	private static Point3D getVector(Point3D p1, Point3D p2) {
		double x = p2.getX() - p1.getX();
		double y = p2.getY() - p1.getY();
		double z = p2.getZ() - p1.getZ();
		return new Point3D(x, y, z);
	}
	
	/**
	 * @param v1 - Vektor 1
	 * @param v2 - Vektor 2
	 * @return Skalarwert der beiden Vektoren.
	 */
	private static double getScalar(Point3D v1, Point3D v2) {
		double scalar = v1.getX()*v2.getX() + v1.getY()*v2.getY() + v1.getZ()*v2.getZ();
		return scalar;
	}
	
	/**
	 * @param v
	 * @return Länge/Betrag des Vektors <i>v</i>.
	 */
	private static double getLength(Point3D v) {
		double length = Math.sqrt(v.getX()*v.getX() + v.getY()*v.getY() + v.getZ()*v.getZ());
		return length;
	}
	
	/**
	 * Überprüft, ob Punkt <i>p</i> innerhalb des Dreiecks <i>ABC</i> liegt.
	 * @param pA - Punkt A des Dreiecks
	 * @param pB - Punkt B des Dreiecks
	 * @param pC - Punkt C des Dreiecks
	 * @param punkt - Punkt zum Überprüfen
	 * @return boolean
	 */
	public static boolean liegtPunktInDreieck(Point3D pA, Point3D pB, Point3D pC, Point3D p) {
		if(p == null) {
			return false;
		}
		
		double winkelAPB = getAngle(p, pA, pB);
		double winkelBPC = getAngle(p, pB, pC);
		double winkelCPA = getAngle(p, pC, pA);
		double winkelsumme = winkelAPB + winkelBPC + winkelCPA;
		
		printDebug("winkelAPB: " + winkelAPB);
		printDebug("winkelBPC: " + winkelBPC);
		printDebug("winkelCPA: " + winkelCPA);	
		printDebug("Winkelsumme: " + winkelsumme);
		
		// Wenn der Punkt innerhalb des Dreiecks ABC ist, ist die WInkelsumme 360° !
		boolean liegtPunktInDreieck = (Math.abs(winkelsumme-360) <= 1.0E-3);
		
		if(liegtPunktInDreieck)
			printDebug("Punkt liegt im Dreieck! :)");
		else
			printDebug("Punkt liegt nicht im Dreieck! :(");		
		
		return liegtPunktInDreieck;	
	}
	
	private static Point3D getCrossProduct(Point3D v1, Point3D v2) {		
		double x = v1.getY()*v2.getZ() - v1.getZ()*v2.getY();
		double y = v1.getZ()*v2.getX() - v1.getX()*v2.getZ();
		double z = v1.getX()*v2.getY() - v1.getY()*v2.getX();
		return new Point3D(x, y, z);		
	}
	
	/**
	 * @param p1 - Punkt A des Dreiecks
	 * @param p2 - Punkt B des Dreiecks
	 * @param p3 - Punkt C des Dreiecks
	 * @return Array mit den Parametern der Ebenengleichung E(x) = a*x + b*y + c*z = d</br>
	 * Array[0] -> Point3D(a,b,c)</br>
	 * Array[1] -> d
	 */
	private static Object[] getEbenengleichung(Point3D p1, Point3D p2, Point3D p3) {
		Point3D v1 = getVector(p1, p2);
		Point3D v2 = getVector(p1, p3);
		
		Point3D normalenVektor = getCrossProduct(v1,v2);
		double value = normalenVektor.getX()*p1.getX() + normalenVektor.getY()*p1.getY() + normalenVektor.getZ()*p1.getZ();
		
		printDebug("Normalenvektor: " + normalenVektor);
		printDebug("Wert: " + value);
		printDebug("Ebenengleichung: " + normalenVektor.getX() + "x + " + normalenVektor.getY() + "y + " + normalenVektor.getZ() + "z = " + value);
		
		Object[] params = new Object[]{normalenVektor, value};		
		return params;
	}
	
	/**
	 * @param p1 - Punkt A der Geraden
	 * @param p2 - Punkt B der Geraden
	 * @return Array mit den Parametern der Geradengleichung g(x) = x + t * v</br>
	 * Array[0] -> Point3D(x1,x2,x3)</br>
	 * Array[1] -> Point3D(v1,v2,v3)
	 */
	private static Point3D[] getGeradengleichung(Point3D p1, Point3D p2) {
		Point3D v = getVector(p1, p2);
		Point3D[] params = new Point3D[]{p1, v};
		return params;
	}
	
	/**
	 * @param paramsEbene - Parameter der Ebenengleichung des Dreiecks, benutze {@link #getEbenengleichung()}
	 * @param paramsGerade - Parameter der Geradengleichung, benutze {@link #getGeradengleichung()}
	 * @return Point3D - Schnittpunkt mit der Ebene des Dreiecks oder <i>null</i>, falls die Gerade parallel zum Dreieck liegt.
	 */
	public static Point3D getIntersectionPoint(Object[] paramsEbene, Point3D[] paramsGerade) {
		Point3D normalenvektor = (Point3D) paramsEbene[0];
		double wertEbene = (double) paramsEbene[1];
		
		Point3D startGerade = paramsGerade[0];
		Point3D vektorGerade = paramsGerade[1];
		
		double value = normalenvektor.getX()*startGerade.getX() + normalenvektor.getY()*startGerade.getY() + normalenvektor.getZ()*startGerade.getZ();
		
		double wertGleichung = wertEbene - value;
		double t = normalenvektor.getX()*vektorGerade.getX() + normalenvektor.getY()*vektorGerade.getY() + normalenvektor.getZ()*vektorGerade.getZ();
		
		if(t == 0) {
			if(wertGleichung == 0) {
				printDebug("Gerade liegt in der Ebene!");
				return null;
			} else {
				printDebug("Gerade liegt parallel zur Ebene!");
				return null;
			}
		} else {
			double n = wertGleichung / t;
			double x = startGerade.getX() + n*vektorGerade.getX();
			double y = startGerade.getY() + n*vektorGerade.getY();
			double z = startGerade.getZ() + n*vektorGerade.getZ();
			Point3D schnittpunkt = new Point3D(x,y,z);
			printDebug("Gerade schneidet Ebene im Punkt: " + schnittpunkt);
			return schnittpunkt;
		}
	}
	
	/**
	 * @param pA - Punkt A des Dreiecks
	 * @param pB - Punkt B des Dreiecks
	 * @param pC - Punkt C des Dreiecks
	 * @param gA - Punkt A der Geraden
	 * @param gB - Punkt B der Geraden
	 * @return Point3D - Schnittpunkt mit der Ebene des Dreiecks oder <i>null</i>, falls die Gerade parallel zum Dreieck liegt.
	 */
	public static Point3D getSchnittpunktDreieckGerade(Point3D pA, Point3D pB, Point3D pC, Point3D gA, Point3D gB) {
		Object[] paramsEbene = getEbenengleichung(pA, pB, pC);
		Point3D[] paramsGerade = getGeradengleichung(gA, gB);
		
		return getIntersectionPoint(paramsEbene, paramsGerade);
	}
	
	private static void printDebug(Object o) {
		if(DEBUG) System.out.println(String.valueOf(o));
	}
	

}
