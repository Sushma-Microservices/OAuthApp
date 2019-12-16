package com.ibm.oauth2.recieveorder;



import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ReceiveOrderController {

	
	@GetMapping("/api/orders/{username}")
	public OrderDetails receiveOrder(@PathVariable String username) {
		
		//Dummy order plugged in 
		OrderDetails order = new OrderDetails();
		order.setOrderId("56789");
		List<String> dummy = new ArrayList<String>();
		dummy.add("Milk");
		dummy.add("Cereal");
		
		order.setItems(dummy);
		return order;
	
	}
	
}
