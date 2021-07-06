import java.io.Serializable;
import java.util.*;

public class Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int id;
	public int source;
	public int destination;
	public int bandwidth;
	public int bandwidthIndex;
	public int splitNum = 0;
	
	public Request(int id, int numNodes, int[] bandwidthSet, Random randomStream){
		this.id = id;
		source = randomStream.nextInt(numNodes);
		destination = randomStream.nextInt(numNodes-1);
		if(destination == source){
			destination = numNodes-1;
		}
		source = source == 0 ? numNodes : source;
		destination = destination == 0 ? numNodes : destination;
		bandwidthIndex = randomStream.nextInt(bandwidthSet.length);
		bandwidth = bandwidthSet[bandwidthIndex];

	}
	
	public Request(Request request){
		this.id = request.id;
		this.source = request.source;
		this.destination = request.destination;
		this.bandwidth = request.bandwidth;
		this.bandwidthIndex = request.bandwidthIndex;
	}
	
	public void setBandwidth(int bandwidth){
		this.bandwidth = bandwidth;
	}
	
	public void printRequest(Request request){
		System.out.println("id: " + request.id + "  S: " + request.source +
				"  D: " + request.destination + "  BW: " + request.bandwidth);
	}
	
	public static void main(String[] args){
		int id = 1;
		int numNodes = 3;
		int[] bandwidthSet = {4, 10, 24};
		Random random = new Random();
		Request request = new Request(id, numNodes, bandwidthSet, random);
		System.out.println(request.id + " " + request.source + " " + request.destination + " " + request.bandwidth);
	}
}