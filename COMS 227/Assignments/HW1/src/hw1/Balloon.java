package hw1;

public class Balloon {
	private double outsideAirTemp;
	private double windDirection;
	private double balloonTemp;
	private double altitude;
	private double fuelRemaining;
	private double fuelBurnRate;
	private double balloonMass;
	private double velocity;
	private double tetherLength;
	private int simulationTime;
	// Constants
	private static final double H = 0.1; // Heat loss factor
	private static final double V = 61234; // Volume of air in the balloon in m^3
	private static final double Ag = 9.81; // Acceleration due to gravity in m/s^2
	private static final double R = 287.05; // Gas constant in J/kgK
	private static final double P = 1013.25; // Standard pressure in hPa
	private static final double Kc = 273.15; // Kelvins at 0 degrees C

	/**
	 * Constructs a new balloon simulation. The simulation starts with the given air
	 * temperature and wind direction.
	 * 
	 * @param airTemp       the outside air temperature in degrees Celsius.
	 * @param windDirection the wind direction in degrees. It is assumed to be
	 *                      between 0 (inclusive) and 360 (exclusive).
	 */
	public Balloon(double airTemp, double windDirection) {
		this.outsideAirTemp = airTemp;
		this.windDirection = windDirection;
		this.fuelRemaining = 0;
		this.balloonTemp = airTemp; // Balloon temperature initialized to the same as outside air
		this.altitude = 0; // Initialized to ground level
		this.fuelBurnRate = 0;
		this.balloonMass = 0;
		this.velocity = 0;
		this.tetherLength = 0;
		this.simulationTime = 0;
	}

	/**
	 * Gives the remaining amount of fuel that can be used to heat the air in the
	 * balloon.
	 * 
	 * @return the remaining fuel in the balloon.
	 */
	public double getFuelRemaining() {
		return fuelRemaining;
	}

	public void setFuelRemaning(double fuel) {
		this.fuelRemaining = fuel;
	}

	public double getBalloonMass() {
		return balloonMass;

	}

	// Gets the mass of the balloon (m in the formulas).
	public void setBalloonMass(double mass) {
		balloonMass = mass;
	}
	// Sets the mass of the balloon (m in the formulas).

	public double getOutsideAirTemp() {
		return outsideAirTemp;
	}

	public void setOutsideAirTemp(double temp) {
		outsideAirTemp = temp;
	}

	public double getFuelBurnRate() {
		return fuelBurnRate;
	}

	public void setFuelBurnRate(double rate) {
		fuelBurnRate = rate;
	}

	public double getBalloonTemp() {
		return balloonTemp;
	}

	public void setBalloonTemp(double temp) {
		balloonTemp = temp;
	}

	public double getVelocity() {
		return velocity;
	}

	public double getAltitude() {
		return altitude;
	}

	public double getTetherLength() {
		return tetherLength;
	}

	public double getTetherRemaining() {
		return tetherLength - altitude;
	}

	public void setTetherLength(double length) {
		tetherLength = length;
	}

	public double getWindDirection() {
		return windDirection;
	}

	public void changeWindDirection(double deg) {
		windDirection += deg;
		windDirection %= 360; // Ensure wind direction is between 0 and 360
		if (windDirection < 0)
			windDirection += 360;
	}

	public long getMinutes() {
		return simulationTime / 60;
	}

	public long getSeconds() {
		return simulationTime % 60;
	}

	public void update() {
		simulationTime++;
		// Consume fuel
		double fuelToConsume = Math.min(fuelBurnRate, fuelRemaining);
		fuelRemaining -= fuelToConsume;

		// Update balloon temperature
		double deltaT = fuelToConsume + (outsideAirTemp - balloonTemp) * H;
		balloonTemp += deltaT;

		// Calculate densities
		double rhoOutside = P / (R * (outsideAirTemp + Kc));
		double rhoBalloon = P / (R * (balloonTemp + Kc));

		// Calculate lift force
		double liftForce = V * (rhoOutside - rhoBalloon) * Ag;

		// Calculate gravitational force
		double gravForce = balloonMass * Ag;

		// Calculate net force
		double netForce = liftForce - gravForce;

		// Calculate net acceleration
		double netAcc = netForce / balloonMass;

		// Update velocity
		velocity += netAcc;

		// Update altitude
		altitude += velocity;

		// Ensure altitude stays within bounds
		altitude = Math.max(0, Math.min(altitude, tetherLength));
	}

	public void reset() {
		outsideAirTemp = 20.0;
		fuelRemaining = 0;
		fuelBurnRate = 0;
		balloonMass = 0;
		balloonTemp = 20.0;
		velocity = 0;
		altitude = 0;
		tetherLength = 0;
		windDirection = 0;
		simulationTime = 0;
	}

}
