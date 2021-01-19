public class NBody{

	 public static double readRadius(String txtfileName){
		/* Start reading in txtfileName */
		In in = new In(txtfileName);
		in.readInt();
		double Radius=in.readDouble();
		return Radius;
	}

	 public static Planet[] readPlanets(String txtfileName){
	 	In in = new In(txtfileName);
	 	int row=in.readInt();
	 	in.readDouble();

	 	Planet[] ps=new Planet[row];
	 	for(int i=0;i<row;i++){
			double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            ps[i] = new Planet(xP, yP, xV, yV, m, img);

	 	}

	 	return ps;

	 }

	 public static void main(String[] args){
	 	double T=Double.parseDouble(args[0]);
	 	double dt=Double.parseDouble(args[1]);
	 	String filename=args[2];
	 	Planet[] ps=readPlanets(filename);
	 	double Radius=readRadius(filename);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-Radius, Radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		for (Planet p : ps) {
			p.draw();
		}

		double time=0;
		double[] xForces=new double[ps.length];
		double[] yForces=new double[ps.length];
		int waitTimeMilliseconds = 10;

		while(time<T){
		for(int i=0;i<ps.length;i++){
			xForces[i]=ps[i].calcNetForceExertedByX(ps);
			yForces[i]=ps[i].calcNetForceExertedByY(ps);
		}
		for(int i=0;i<ps.length;i++){
			ps[i].update(dt,xForces[i],yForces[i]);
		}
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for(Planet p : ps){
			p.draw();
		}
		StdDraw.show();
		StdDraw.pause(waitTimeMilliseconds);
		time+=dt;
		}

		StdOut.printf("%d\n", ps.length);
		StdOut.printf("%.2e\n", Radius);
		for (int i = 0; i < ps.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
			              ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
											}   


	}


}
