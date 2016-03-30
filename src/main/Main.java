package main;
import javafx.geometry.Point3D;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
//		Utils.DEBUG = true;
		
		berechneWinkel();
		liegtPunktInDreieck();
		SchnittpunktGeradeMitDreieck();
		SchnittpunktViereckGerade();
	}
	
	
	private static void berechneWinkel() {
		/**
		 * 	 P2 (0,1,0)
		 * 	 |
		 * 	 |
		 * 	 |___
		 * 	 | a |
		 * 	 P1----------P3 (1,0,0)
		 * (0,0,0)
		 */
		
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(1, 0, 0);
		
		double winkelA = Utils.getAngle(p1, p2, p3);
		
		System.out.println("winkelA: " + winkelA);
		System.out.println("===================\n");
	}
	
	private static void liegtPunktInDreieck() {
		/**
		 * 	       C ((1, 2, 0)
		 * 	     /   \
		 * 	    /     \
		 * 	   /   X   \
		 * 	  /         \
		 * 	 A-----------B (2,0,0)
		 * (0,0,0)
		 */		
		
		
		// Dreieck
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 0);
		Point3D pC = new Point3D(1, 2, 0);
		
		// Punkt / Schnittpunkt im Dreieck
		Point3D X = new Point3D(1, 1, 0);
		
		boolean imDreieck = Utils.liegtPunktImDreieck(pA, pB, pC, X);
		System.out.println("Punkt im Dreieck: " + imDreieck);
		System.out.println("===================\n");
	}
	
	private static void SchnittpunktGeradeMitDreieck() {
		
		// Dreieck
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 0);
		Point3D pC = new Point3D(1, 2, 0);
		
		// Gerade
		Point3D gA = new Point3D(1, 1, 1);
		Point3D gB = new Point3D(1, 1, -1);		
		
		// Schnittpunkt
		Point3D schnittpunkt = Utils.getSchnittpunktDreieckGerade(pA, pB, pC, gA, gB);
		System.out.println("Schnittpunkt: " + schnittpunkt);
		
		// im Dreieck
		boolean imDreieck = Utils.liegtPunktImDreieck(pA, pB, pC, schnittpunkt);
		System.out.println("Punkt im Dreieck: " + imDreieck);	
		System.out.println("===================\n");
	}
	
	private static void SchnittpunktViereckGerade() {
				
		// Viereck
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 0);
		Point3D pC = new Point3D(2, 2, 0);
		Point3D pD = new Point3D(0, 2, 0);
		
		// Gerade
		Point3D gA = new Point3D(1, 1, 1);
		Point3D gB = new Point3D(1, 1, -1);
		
		Point3D schnittpunkt = Utils.getSchnittpunktViereckGerade(pA, pB, pC, pD, gA, gB);
		System.out.println("Schnittpunkt: " + schnittpunkt);
		
		boolean imViereck = Utils.liegtPunktImViereck(pA, pB, pC, pD, schnittpunkt);
		System.out.println("Punkt im Viereck: " + imViereck);
		System.out.println("===================\n");
	}
	

}
