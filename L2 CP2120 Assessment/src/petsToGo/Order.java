package petsToGo;

public class Order {
	private Supplier supplier;
	private Money cost;
	private Pet petFromSupplier;
	private int ref;

	public Order() {
		ref = 0;
	}

	/**
	 * @param isPaidFor
	 * @param supplier
	 * @param cost
	 * @param dateOfPurchase
	 * @param petFromSupplier
	 */
	public Order(Supplier supplier, Money cost, Pet petFromSupplier) {
		this.supplier = supplier;
		this.cost = cost;
		this.petFromSupplier = petFromSupplier;
		String refString = petFromSupplier.getPetID() + "" + supplier.getSupplierID();
		this.ref = Integer.parseInt(refString);
	}

	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier
	 *            the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the cost
	 */
	public Money getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(Money cost) {
		this.cost = cost;
	}

	/**
	 * @return the petFromSupplier
	 */
	public Pet getPetFromSupplier() {
		return petFromSupplier;
	}

	/**
	 * @param petFromSupplier
	 *            the petFromSupplier to set
	 */
	public void setPetFromSupplier(Pet petFromSupplier) {
		this.petFromSupplier = petFromSupplier;
	}

	/**
	 * @return the ref
	 */
	public int getRef() {
		return ref;
	}

	/**
	 * @param ref
	 *            the ref to set
	 */
	public void setRef(int ref) {
		String refString = petFromSupplier.getPetID() + "" + supplier.getSupplierID();
		this.ref = Integer.parseInt(refString);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Order) {
			Order order = (Order) obj;
			if (order.petFromSupplier.getNameType().equals(petFromSupplier.getNameType()) 
					&& order.supplier.getSupplierName().equals(supplier.getSupplierName())) {
				return true;
			}
		}
		return false;
	}
}