import java.util.*;
public class Bill {
	static Map<String, Float> product_info = new HashMap<String, Float>();
	static Map<String, Float> cust_info = new HashMap<String, Float>();
	static Map<String, String> dist_info = new HashMap<String, String>();
	public static void main(String[] args) throws InputMismatchException, NumberFormatException{
		try {
			Scanner scan = new Scanner(System.in);
			Scanner scan1 = new Scanner(System.in);
			//List<String> prod_info = new ArrayList<String>();
			float sum =0;
			int no_of_products = scan.nextInt();
			//System.out.println(no_of_products);
			for(int i=0; i<no_of_products; i++) {
				//System.out.println("Megha");
				String temp = scan1.nextLine();
				//System.out.println(temp);
				String temp_split[] = temp.split("\\s+");
				//System.out.println(temp_split[2]);
				//float temp_dis = Float.parseFloat((temp_split[1]));
				
				//product_info.put(temp_split[0],temp_dis);
				product_info.put(temp_split[0].toString(),Float.parseFloat(temp_split[1]));
			}
			//System.out.println(product_info);
			float discount_coupon = scan.nextFloat();
			int no_of_enteries = scan.nextInt();
		//		System.out.println(product_info);
			for(int i=0; i<no_of_enteries; i++) {
				//String excel_entry = scan.next();
				String temp_entry[] = scan1.nextLine().split("\\s+");
				//System.out.println(temp_entry);
				String cust_id = temp_entry[0];
				String prdt = temp_entry[1];
				int qty = Integer.parseInt(temp_entry[2]);
				float price = Float.parseFloat(temp_entry[3]);
				String dis_coupon = temp_entry[4];
				dist_info.put(cust_id, dis_coupon);
				for(Map.Entry<String, Float> entry1 : cust_info.entrySet()) {
					
					if(entry1.getKey().equalsIgnoreCase(cust_id)) {
						sum = entry1.getValue();
						//System.out.println(sum);
						break;
					}
					else {
						sum = 0;
						
						dist_info.put(cust_id, dis_coupon);
						
					}	
				}

				for (Map.Entry<String, Float> entry2 : product_info.entrySet()) {
				    String key = entry2.getKey();
				    float val = entry2.getValue();
				    if(key.equalsIgnoreCase(prdt)) {
				    	sum += (price - (price*val)/100)*qty;
				    	//System.out.println(sum);
				    	cust_info.put(cust_id, sum);
				    }
				}
			}
			
			//System.out.println(dist_info);
			for (Map.Entry<String, String> entry2 : dist_info.entrySet()) {
			    if(entry2.getValue().equalsIgnoreCase("yes")) {
			    	
			    	float sum_after_discount = cust_info.get(entry2.getKey()) - discount_coupon;
			    	cust_info.put(entry2.getKey(), sum_after_discount);
			    }
			}
			
			
			for (Map.Entry<String, Float> entry2 : cust_info.entrySet()) {
				System.out.println(entry2.getKey()+"  "+entry2.getValue());
				
			}
			
			scan.close();
			scan1.close();
		}
		catch(InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}

