public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private double G=6.67e-11;

//here are two constructers
	public Planet(double xxPos,double yyPos,double xxVel,
		double yyVel,double mass,String imgFileName){
		this.xxPos=xxPos;
		this.yyPos=yyPos;
		this.xxVel=xxVel;
		this.yyVel=yyVel;
		this.mass=mass;
		this.imgFileName=imgFileName;
	}

	public Planet(Planet p){
		this.xxPos=p.xxPos;
		this.yyPos=p.yyPos;
		this.xxVel=p.xxVel;
		this.yyVel=p.yyVel;
		this.mass=p.mass;
		this.imgFileName=p.imgFileName;
	}

//this method aim to calculate the distance between this Planet and Planet p
	public double calcDistance(Planet p){
		double dx=p.xxPos-this.xxPos;
		double dy=p.yyPos-this.yyPos;
		return Math.sqrt(dx*dx+dy*dy);
	}

//this method aim to calculate the Force between this Planet and Planet p
	public double calcForceExertedBy(Planet p){
		double f=G*this.mass*p.mass/(this.calcDistance(p)*this.calcDistance(p));
		return f;
	}

//these method aim to calculate the Forcex and Forcey between this Planet and Planet p
	public double calcForceExertedByX(Planet p){
		double dx=p.xxPos-this.xxPos;
		double cos=dx/this.calcDistance(p);
		double fx=this.calcForceExertedBy(p)*cos;
		return fx;
	}

	public double calcForceExertedByY(Planet p){
		double dy=p.yyPos-this.yyPos;
		double cos=dy/this.calcDistance(p);
		double fy=this.calcForceExertedBy(p)*cos;
		return fy;
	}

//these methods aim to calculate Forcex_net and Forcey_net
	public double calcNetForceExertedByX(Planet[] p){
		double fx_net=0;
		for(int i=0;i<p.length;i++){
			if(!this.equals(p[i])){
				fx_net+=this.calcForceExertedByX(p[i]);
			}
		}
		return fx_net;
		}

	public double calcNetForceExertedByY(Planet[] p){
		double fy_net=0;
		for(int i=0;i<p.length;i++){
			if(!this.equals(p[i])){
				fy_net+=this.calcForceExertedByY(p[i]);
			}
		}
		return fy_net;
	}

//this method aim to update the basic message of this planet
	public void update(double dt,double fx,double fy){
		double ax=fx/this.mass;
		double ay=fy/this.mass;
		this.xxVel+=ax*dt;
		this.yyVel+=ay*dt;
		this.xxPos+=this.xxVel*dt;
		this.yyPos+=this.yyVel*dt;
	}
	
	public void draw() {
		String filename = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, filename);	
	}

}
