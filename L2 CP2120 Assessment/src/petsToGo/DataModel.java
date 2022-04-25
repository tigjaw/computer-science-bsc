package petsToGo;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class DataModel {
	private Inventory inventory = new Inventory();
	private SaleList saleList = new SaleList();
	private SupplierList supplierList = new SupplierList();
	private OrderList orderList = new OrderList();
	private CustomerList custList = new CustomerList();

	public void addPet(Pet pet1) {
		inventory.addPet(pet1);
	}

	public void removePet(int id) {
		inventory.remPet(id);
	}

	public LinkedList<Pet> getPets(int option) {
		LinkedList<Pet> viewPets = new LinkedList<Pet>();
		switch (option) {
			case 0:
				viewPets = inventory.listAllPets();
				break;
			case 1: 
				viewPets = inventory.listBirds();
				break;
			case 2: 
				viewPets = inventory.listMammals();
				break;
			case 3: 
				viewPets = inventory.listReptiles();
				break;
			case 4:
				viewPets = inventory.listAquatics();
				break;
			case 5:
				viewPets = inventory.listFresh();
				break;
			case 6:
				viewPets = inventory.listMarines();
				break;
		}
		return viewPets;
	}

	public void addCustomer(Customer cust) {
		custList.addCust(cust);
	}

	public void removeCustomer(int id) {
		custList.remCust(id);
	}

	public LinkedList<Customer> getAllCustomers() {
		LinkedList<Customer> viewCustomers = new LinkedList<Customer>();
		viewCustomers = custList.listAllCustomers();
		return viewCustomers;
	}

	public void recordSale(Sale sale) {
		saleList.addSale(sale);
	}

	public LinkedList<Sale> getAllSales() {
		LinkedList<Sale> sales = new LinkedList<Sale>();
		sales = saleList.listAllSales();
		return sales;
	}
	
	public LinkedList<Object> showRevenues() {
		LinkedList<Object> allSales = new LinkedList<Object>();
		LinkedList<Object> mammalSales = new LinkedList<Object>();
		mammalSales = saleList.listMammalSales();
		LinkedList<Object> birdSales = new LinkedList<Object>();
		birdSales = saleList.listBirdSales();
		LinkedList<Object> reptileSales = new LinkedList<Object>();
		reptileSales = saleList.listReptileSales();
		LinkedList<Object> marineSales = new LinkedList<Object>();
		marineSales = saleList.listMarineSales();
		LinkedList<Object> freshSales = new LinkedList<Object>();
		freshSales = saleList.listFreshwaterSales();
		allSales.add(mammalSales);
		allSales.add(birdSales);
		allSales.add(reptileSales);
		allSales.add(freshSales);
		allSales.add(marineSales);
		return allSales;
	}

	public void recordOrder(Order order) {
		orderList.addOrder(order);
	}
	
	public LinkedList<Order> getAllOrders() {
		LinkedList<Order> viewOrders = new LinkedList<Order>();
		viewOrders = orderList.listAllOrders();
		return viewOrders;
	}

	public LinkedList<Order> getCurrentOrders() {
		LinkedList<Order> viewOrders = new LinkedList<Order>();
		viewOrders = orderList.listOnlyOrders();
		return viewOrders;
	}

	public LinkedList<Order> getCompletePurchases() {
		LinkedList<Order> viewOrders = new LinkedList<Order>();
		viewOrders = orderList.listOnlyPurchases();
		return viewOrders;
	}

	public LinkedList<Supplier> listAllSuppliers() {
		LinkedList<Supplier> viewSuppliers = new LinkedList<Supplier>();
		viewSuppliers = supplierList.listAllSuppliers();
		return viewSuppliers;
	}

	public void payForOrder(int id, GregorianCalendar datePaid) {
		orderList.purchaseOrder(id, datePaid);
	}

	public void addSupplier(Supplier supp) {
		supplierList.addSupplier(supp);
	}

	public void removeSupplier(int id) {
		supplierList.remSupplier(id);
	}

	/**
	 * Saves data to XML
	 */
	public void saveData() {
		inventory.saveData();
		supplierList.saveData();
		custList.saveData();
		// save all data from all linkedLists
	}
}