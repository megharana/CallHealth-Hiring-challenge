import java.util.*;


public class Voucher {
	static ArrayList<ArrayList<Integer>>flights = new ArrayList<ArrayList<Integer>>();
	static void group_flights(int call, String[] connections) {
		
		
		ArrayList<Integer>flight = new ArrayList<Integer>();
		boolean found = false;
		if(call == 0) {
			flight.add(Integer.parseInt(connections[0]));
			flight.add(Integer.parseInt(connections[1]));
			flights.add(flight);
		}
		else {
			for(List<Integer> flight_iterator :flights)	{
				if(flight_iterator.contains(Integer.parseInt(connections[0])) ||flight_iterator.contains(Integer.parseInt(connections[1]))) {
					found = true;
					flights.remove(flight_iterator);
					flight_iterator.add(Integer.parseInt(connections[0]));
					flight_iterator.add(Integer.parseInt(connections[1]));
					Set<Integer> flight_distinct = new HashSet<Integer>(flight_iterator);
					//System.out.println(flight_distinct);
					ArrayList<Integer>flight_distinct_list = new ArrayList<Integer>();
					for(int f : flight_distinct)
						flight_distinct_list.add(f);
					flights.add(flight_distinct_list);
					break;
				}
			}
			if(!found) {
			
				flight.add(Integer.parseInt(connections[0]));
				flight.add(Integer.parseInt(connections[1]));
				flights.add(flight);
			}
					
			
		}
		//flights.add(flight);
		
		
	}
	
	static ArrayList<ArrayList<Integer>> left_connection(int N, ArrayList<ArrayList<Integer> >final_flights) {
		ArrayList<Integer> left_passenger = new ArrayList<Integer>();
		try {
			for(List<Integer> flight_iterator :final_flights)	{
				
					for(int i = 1; i <= N; i++) {	
						if(!flight_iterator.contains(i)) {
							left_passenger.add(i);
							break;
						}
						
					}
					final_flights.add(left_passenger);
			}

		}catch(ConcurrentModificationException e) {
			e.printStackTrace();
		}
		return final_flights;
		
	}
	static ArrayList<Integer>get_winner(ArrayList<ArrayList<Integer>>flight_list, int K){
		ArrayList<Integer>winner_list = new ArrayList<Integer>();
		for(List<Integer> flight_iterator :flight_list)	{
			//System.out.println(flight_iterator);
			if(flight_iterator.size()>1)
				winner_list.add(flight_iterator.get(K-1));
			
		}
		return winner_list;
		
	}
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String input[] = scan.nextLine().split("\\s+");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		ArrayList<ArrayList<Integer>>resultant_flights = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < M; i++) {
			String connections[] = scan.nextLine().split("\\s+");
			group_flights(i,connections);
		}
		resultant_flights = left_connection(N, flights);
		//System.out.println(resultant_flights);
		ArrayList<Integer>passenger_get_voucher = get_winner(resultant_flights,K);
		System.out.println(passenger_get_voucher.size());
		System.out.println(passenger_get_voucher);
		scan.close();
	}

}
