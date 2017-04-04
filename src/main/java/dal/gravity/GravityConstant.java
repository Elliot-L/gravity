package dal.gravity;

public class GravityConstant implements GravityModel{
	// g holds the gravity constant for this model
	private double g;
	
	//public constructor
	public GravityConstant(double g){
		this.g = g;
	}
	
	public double getGravitationalField(){
		return g;
	}


}
