package test;

import org.junit.Assert;
import org.junit.Test;

import javafx.geometry.Point3D;
import main.Utils;

public class UtilsTest {

	private final double ERROR = 0.00000001;

	@Test
	public void GeradeSchneidetDreieck() {
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 0);
		Point3D pC = new Point3D(1, 2, 0);

		Point3D gA = new Point3D(1, 1, 1);
		Point3D gB = new Point3D(1, 1, -1);

		Point3D schnittpunkt = Utils.getSchnittpunktDreieckGerade(pA, pB, pC, gA, gB);

		Assert.assertEquals(1.0, schnittpunkt.getX(), ERROR);
		Assert.assertEquals(1.0, schnittpunkt.getY(), ERROR);
		Assert.assertEquals(0.0, schnittpunkt.getZ(), ERROR);

		boolean imDreieck = Utils.liegtPunktInDreieck(pA, pB, pC, schnittpunkt);

		Assert.assertTrue(imDreieck);
	}

	@Test
	public void GeradeSchneidetDreieck2() {
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 0);
		Point3D pC = new Point3D(1, 2, 0);

		Point3D gA = new Point3D(0, 0, 1);
		Point3D gB = new Point3D(2, 2, -1);

		Point3D schnittpunkt = Utils.getSchnittpunktDreieckGerade(pA, pB, pC, gA, gB);

		Assert.assertEquals(1.0, schnittpunkt.getX(), ERROR);
		Assert.assertEquals(1.0, schnittpunkt.getY(), ERROR);
		Assert.assertEquals(0.0, schnittpunkt.getZ(), ERROR);

		boolean imDreieck = Utils.liegtPunktInDreieck(pA, pB, pC, schnittpunkt);

		Assert.assertTrue(imDreieck);
	}

	@Test
	public void GeradeSchneidetDreieck3() {
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 2);
		Point3D pC = new Point3D(1, 4, 4);

		Point3D gA = new Point3D(1, 1, 1);
		Point3D gB = new Point3D(1, 1, -1);

		Point3D schnittpunkt = Utils.getSchnittpunktDreieckGerade(pA, pB, pC, gA, gB);

		Assert.assertEquals(1.0, schnittpunkt.getX(), ERROR);
		Assert.assertEquals(1.0, schnittpunkt.getY(), ERROR);
		Assert.assertEquals(1.75, schnittpunkt.getZ(), ERROR);

		boolean imDreieck = Utils.liegtPunktInDreieck(pA, pB, pC, schnittpunkt);

		Assert.assertTrue(imDreieck);
	}

	@Test
	public void GeradeSchneidetDreieckAusserhalb() {
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 0);
		Point3D pC = new Point3D(1, 2, 0);

		Point3D gA = new Point3D(2, 2, 2);
		Point3D gB = new Point3D(2, 2, -1);

		Point3D schnittpunkt = Utils.getSchnittpunktDreieckGerade(pA, pB, pC, gA, gB);

		Assert.assertEquals(2.0, schnittpunkt.getX(), ERROR);
		Assert.assertEquals(2.0, schnittpunkt.getY(), ERROR);
		Assert.assertEquals(0.0, schnittpunkt.getZ(), ERROR);

		boolean imDreieck = Utils.liegtPunktInDreieck(pA, pB, pC, schnittpunkt);

		Assert.assertFalse(imDreieck);
	}

	@Test
	public void GeradeSchneidetDreieckInEcke() {
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 0);
		Point3D pC = new Point3D(1, 2, 0);

		Point3D gA = new Point3D(0, 0, 1);
		Point3D gB = new Point3D(0, 0, -1);

		Point3D schnittpunkt = Utils.getSchnittpunktDreieckGerade(pA, pB, pC, gA, gB);

		Assert.assertEquals(0.0, schnittpunkt.getX(), ERROR);
		Assert.assertEquals(0.0, schnittpunkt.getY(), ERROR);
		Assert.assertEquals(0.0, schnittpunkt.getZ(), ERROR);

		boolean imDreieck = Utils.liegtPunktInDreieck(pA, pB, pC, schnittpunkt);

		Assert.assertTrue(imDreieck);
	}

	@Test
	public void GeradeParallelZumDreieck() {
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 0);
		Point3D pC = new Point3D(1, 2, 0);

		Point3D gA = new Point3D(1, 1, 0);
		Point3D gB = new Point3D(2, 4, 0);

		Point3D schnittpunkt = Utils.getSchnittpunktDreieckGerade(pA, pB, pC, gA, gB);

		Assert.assertNull(schnittpunkt);
	}

	@Test
	public void GeradeLiegtImDreieck() {
		Point3D pA = new Point3D(0, 0, 0);
		Point3D pB = new Point3D(2, 0, 0);
		Point3D pC = new Point3D(1, 2, 0);

		Point3D gA = new Point3D(1, 1, 0);
		Point3D gB = new Point3D(2, 4, 0);

		Point3D schnittpunkt = Utils.getSchnittpunktDreieckGerade(pA, pB, pC, gA, gB);

		Assert.assertNull(schnittpunkt);
	}

	@Test
	public void Winkel() {
		Point3D s = new Point3D(0, 0, 0);
		Point3D p1 = new Point3D(2, 0, 0);
		Point3D p2 = new Point3D(0, 2, 0);
		Assert.assertEquals(90.0, Utils.getAngle(s, p1, p2), ERROR);

		s = new Point3D(0, 0, 0);
		p1 = new Point3D(2, 0, 0);
		p2 = new Point3D(0, 2, 2);
		Assert.assertEquals(90.0, Utils.getAngle(s, p1, p2), ERROR);

		s = new Point3D(0, 0, 0);
		p1 = new Point3D(1, 0, 0);
		p2 = new Point3D(1, 1, 0);
		Assert.assertEquals(45.0, Utils.getAngle(s, p1, p2), ERROR);

		s = new Point3D(0, 0, 0);
		p1 = new Point3D(1, 1, 0);
		p2 = new Point3D(-1, 1, 0);
		Assert.assertEquals(90.0, Utils.getAngle(s, p1, p2), ERROR);

		s = new Point3D(0, 0, 0);
		p1 = new Point3D(1, 0, 0);
		p2 = new Point3D(-1, 1, 0);
		Assert.assertEquals(135.0, Utils.getAngle(s, p1, p2), ERROR);

		s = new Point3D(0, 0, 0);
		p1 = new Point3D(1, 0, 0);
		p2 = new Point3D(-1, 0, 0);
		Assert.assertEquals(180.0, Utils.getAngle(s, p1, p2), ERROR);

		s = new Point3D(0, 0, 0);
		p1 = new Point3D(1, 0, 0);
		p2 = new Point3D(0, -1, 0);
		Assert.assertEquals(90.0, Utils.getAngle(s, p1, p2), ERROR);
	}

	@Test
	public void equals() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(0, 0, 0);

		Assert.assertEquals(p1, p2);
		Assert.assertTrue(p1.equals(p2));
		Assert.assertFalse(p1 == p2);
	}

}
