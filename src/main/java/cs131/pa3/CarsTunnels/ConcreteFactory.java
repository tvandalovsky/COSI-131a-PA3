/*
 * Thomas Vandalovsky
 * COSI 131a: OS PA3
 */
package cs131.pa3.CarsTunnels;

import cs131.pa3.Abstract.Direction;
import cs131.pa3.Abstract.Factory;
import cs131.pa3.Abstract.Tunnel;
import cs131.pa3.Abstract.Vehicle;

/**
 * The class implementing the Factory interface for creating instances of classes
 * @author cs131a
 *
 */
public class ConcreteFactory implements Factory {
	
	
	/**
	 * creates new BasicTunnel with Name name and returns that tunnel
	 * @param String name of the tunnel
	 */
    @Override
    public Tunnel createNewBasicTunnel(String name){
    	BasicTunnel tunnel = new BasicTunnel(name);
    	return tunnel;
    	//throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    /**
	 * creates new Car car, with Name name and Direction direction of either north or south
	 * @param String name of the Car, and the direction of that Car
	 */
    @Override
    public Vehicle createNewCar(String name, Direction direction){
    	Car car = new Car(name, direction);
    	return car;
    	//throw new UnsupportedOperationException("Not supported yet.");
    }

    
    /**
	 * creates new Sled sled, with Name name and Direction direction of either north or south
	 * @param String name of the Sled, and the direction of that Sled
	 */    
    @Override
    public Vehicle createNewSled(String name, Direction direction){
    	Sled sled = new Sled(name, direction);
    	return sled;
    	//throw new UnsupportedOperationException("Not supported yet.");
    }
}
