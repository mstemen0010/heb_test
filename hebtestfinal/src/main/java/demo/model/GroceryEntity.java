package demo.model;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GroceryEntity {
	@Id @GeneratedValue
    private long id; 
	private int sku;
    private String Description;
    private String lastSold;
    private int ShelfLife;
    private String Department;
    private float Price;
    private int Unit;
    private int xFor;
    private float Cost;
 
    public GroceryEntity() {
    	
    }
    
    public GroceryEntity (int sku, String description, String lastSold, int shelfLife, String department, float price, int unit, int xFor, float cost ) {
    	this.setSku(sku);
    	this.setDescription(description);
    	this.setLastSold(lastSold);
    	this.setShelfLife(shelfLife);
    	this.setDepartment(department);
    	this.setPrice(price);
    	this.setUnit(unit);
    	this.setxFor(xFor);
    	this.setCost(cost);
    }
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getLastSold() {
		return lastSold;
	}

	public void setLastSold(String lastSold) {
		this.lastSold = lastSold;
	}

	public int getShelfLife() {
		return ShelfLife;
	}

	public void setShelfLife(int shelfLife) {
		ShelfLife = shelfLife;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public int getUnit() {
		return Unit;
	}

	public void setUnit(int unit) {
		Unit = unit;
	}

	public int getxFor() {
		return xFor;
	}

	public void setxFor(int xFor) {
		this.xFor = xFor;
	}

	public float getCost() {
		return Cost;
	}

	public void setCost(float cost) {
		Cost = cost;
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}
 
}