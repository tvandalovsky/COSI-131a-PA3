/*
 * Thomas Vandalovsky
 * COSI 131a: OS PA3
 */
package cs131.pa3.CarsTunnels;

import cs131.pa3.Abstract.Direction;
import cs131.pa3.Abstract.Tunnel;
import cs131.pa3.Abstract.Vehicle;

/**
 * 
 * The class for the Basic Tunnel, extending Tunnel.
 * @author cs131a
 * @author thomas_vandalovsky
 * @fields carCounter: counter for number of cars in the tunnel, sledCounter: counter for number of sleds in the tunnel, 
 * 				isCarTunnel: true if car tunnel and false if in sled tunnel, direc: the direction of the tunnel (either North or South) 
 */
public class BasicTunnel extends Tunnel{
	int carCounter = 0; 
	int sledCounter = 0; 
	boolean isCarTunnel; 
	Direction direc;
	
	/**
	 * Creates a new instance of a basic tunnel with the given name
	 * @param name the name of the basic tunnel
	 */
	public BasicTunnel(String name) {
		super(name);
	}
	
	
	/**
	 * logic and checks for whether a vehicle is allowed within a certain tunnel 
	 * these vehicles are either a sled or a car and must follow certain restrictions 
	 * Car tunnels: can have no more than 3 cars, must be going the same direction, and cannot have a sled in its tunnel 
	 * Sled tunnel: can only have 1 sled in the tunnel at a time, must be going the same direction, cannot have cars in its tunnel
	 * @param Vehicle object named vehicle that is checked if it can enter the tunnel
	 * @return boolean on whether the vehicle is entered into the tunnel (true) or not (false)
	 */
	@Override
	protected synchronized boolean tryToEnterInner(Vehicle vehicle) {
		
		//if the tunnel is empty 
		if(carCounter == 0 && sledCounter == 0) { 
			
			//if the vehicle is a sled then the tunnel becomes a sled tunnel, sledCounter is incremented, boolean for carTunnel is false,
			//direc is set for sleds direction
			if(vehicle instanceof Sled) { 
				sledCounter++; 
				isCarTunnel = false;
				direc = vehicle.getDirection();
				return true;
			}
			
			//if the vehicle is a car then the tunnel becomes a car tunnel, carCounter is incremented, boolean for carTunnel is true,
			//direc is set for cars direction
			if(vehicle instanceof Car) { 
				carCounter++;
				isCarTunnel = true;
				direc = vehicle.getDirection();
				return true;
			}
			
			//should not execute unless weird vehicle is passed in
			return false;
		}
		else {
			
			//the restrictions if it is a car tunnel based on boolean
			if(isCarTunnel == true) { 
				
				//if vehicle is a sled trying to enter a car tunnel, return false
				if(vehicle instanceof Sled) { 
					return false;
				}
				
				//if cars direction does not match tunnel returns false
				if(vehicle.getDirection() != direc) { 
					return false;
				}
				
				//car tunnels can only have three cars at a time, so if counter is 3 and not 0,1,or 2, returns false
				if(carCounter == 3) {
					return false;
				}
				
				//passes restrictions then allowed into tunnel and carCounter increments by 1
				else { 
					carCounter++;
					return true;
				}
			}
			
			//the restrictions if it is a sled tunnel based on boolean 
			if(isCarTunnel == false) {
				
				//if vehicle is a car trying to enter a sled tunnel, return false
				if(vehicle instanceof Car) { 
					return false; 
				}
				
				//if sleds direction does not match tunnel returns false
				if(vehicle.getDirection() != direc) { 
					return false;
				}
				
				//sled tunnels can only have one sled at a time, so if counter is 1 and not zero, returns false
				if(sledCounter == 1) { 
					return false;
				}
				
				//should never execute since tunnel either has 1 sled or zero sleds
				else {
					sledCounter++;
					return true;
				}
			}
			
			//returns false if everything else fails
			return false;
			
		}
	}
	
	/**
	 * decrements the counter of the vehicle 
	 * if the vehicle passed in is a Sled, sledCounter is decremented
	 * if the vehicle passed in is a Car, carCounter is decremented
	 * @param Vehicle object named vehicle that is exiting the tunnel
	 */
	@Override
	protected synchronized void exitTunnelInner(Vehicle vehicle) {
		
		//decrements sledCounter
		if(vehicle instanceof Sled) {
			sledCounter--;
		}
		
		//decrements carCounter
		if(vehicle instanceof Car) { 
			carCounter--;
		}
	}
	
}
