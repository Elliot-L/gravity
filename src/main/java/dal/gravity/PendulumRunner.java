package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {

    public static void main (String [] args) {
	
    //creating a gravity constant for earth and for saturn
    	
    GravityConstant earth = new GravityConstant(9.80665);
    GravityConstant saturn = new GravityConstant(25.0);
    	
    NumberFormat nf = NumberFormat.getInstance ();
	nf.setMaximumFractionDigits (3);

	double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
	double sLen = 10, pMass = 10, theta0 = Math.PI/30;
	RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta, earth);
	SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, earth);
	//RegularPendulum rpCoarse = 
	  //  new RegularPendulum (sLen, pMass, theta0, .1, earth);

	// print out difference in displacement in 1 second intervals
	// for 20 seconds
	int iterations = (int) (1/delta);
	System.out.println ("analytical vs. numerical displacement with Earth gravitational field");
	for (int second = 1; second <= 20; second++) {
	    for (int i = 0; i < iterations; i++) rp.step ();
	   // for (int i = 0; i < 10; i++) rpCoarse.step (); 
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ())));
			//	+ "\t" + 
			//	nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}
	
	//running things with saturn gravity
	System.out.println ("analytical vs. numerical displacement with Saturn gravitational field");
	sp.setGravityConstant(saturn);
	rp.setGravityConstant(saturn);
	for (int second = 1; second <= 20; second++) {
	    for (int i = 0; i < iterations; i++) rp.step ();
	   // for (int i = 0; i < 10; i++) rpCoarse.step (); 
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ())));
			//	+ "\t" + 
			//	nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}
	
  }//ends main method
}//ends class

