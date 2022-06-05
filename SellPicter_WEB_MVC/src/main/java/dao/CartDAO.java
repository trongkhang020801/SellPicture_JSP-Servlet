
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


import model.Items;

/**
 * @author MyPC
 *
 */
public class CartDAO {
	private HashMap<Long, Items> cartItems;

	public HashMap<Long, Items> getCartItems() {
		return cartItems;
	}

	public void setCartItems(HashMap<Long, Items> cartItems) {
		this.cartItems = cartItems;
	}

	public CartDAO(){
		cartItems = new HashMap<Long, Items>();
	}

	public CartDAO(HashMap<Long, Items> cartItems) { 
		this.cartItems = cartItems;
	}


	public void insertToCart(Long key, Items item) {
		boolean check = cartItems.containsKey(key);
		
		if(check) {
			int quantity_old = item.getQuantity();
			item.setQuantity(quantity_old+1);
			cartItems.put(key, item);
		}
		else {
			cartItems.put(key, item);
		}
	}
	
	public void updateToCart(Long key, int quantity, Items item) {
		boolean check = cartItems.containsKey(key);
		
		if(check) {
			int quantity_old = item.getQuantity();
			item.setQuantity(quantity);
			cartItems.put(key, item);
		} 
	}

	
	public void removeToCart(Long key) {
		boolean check = cartItems.containsKey(key);
		
		if(check) {
			cartItems.remove(key);
		}
	}
	
	public int countItems() {
		
		return cartItems.size();
	}

	DecimalFormat numformat = new DecimalFormat("#,###,###");
	
	public String totalCart() { 
		double count = 0;
		for (Map.Entry<Long, Items> list : cartItems.entrySet()) {
			double cost = list.getValue().getProducts().getGiaGoc();
			int discount = list.getValue().getProducts().getKhuyenMai(); 
			double total = cost -(cost*discount)/100; 
			count += total * list.getValue().getQuantity(); 
		}
		String price_nb = numformat.format(count);
		return price_nb;
	} 



}
