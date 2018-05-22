package demo.repository;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import demo.model.GroceryEntity;

@RepositoryRestResource(collectionResourceRel = "grocery_item", path = "grocery_item")
public interface GroceryEntityRepository extends PagingAndSortingRepository<GroceryEntity, Long> {
	
	public enum  GroceryEntityField {
		GroceryEntitInvalid("Invalid", "Invalid"),
		GroceryEntityUndefined("Undefined Item", "Undefined"),
		GroceryEntityID("ID", "ID"), 
		GroceryEntityDescription("Description", "description"),
		GroceryEntitylastSold("Last Sold", "last_sold"),
		GroceryEntityShelfLife("Shelf Life", "self_life"),
		GroceryEntityDepartment("Department", "department"),
		GroceryEntityPrice("Price", "price"),
		GroceryEntityUnit("Unit", "unit" ),
		GroceryEntityXFor("xFor", "xfor"),
		GroceryEntityCost("Cost", "cost");
		
		String stateHandle;
		String dbFieldHandle;
		
		GroceryEntityField( String newStateHandle, String newDbFieldHandle ) {
			this.stateHandle = newStateHandle; // display field
			this.dbFieldHandle = newDbFieldHandle;
		}
		
		static public GroceryEntityField convertStrToField( String stringToConvert) throws IllegalArgumentException {
			if( stringToConvert == null ) {
				return GroceryEntitInvalid;
			}
			GroceryEntityField gdtRes = GroceryEntityUndefined;
			try {			
				gdtRes =  GroceryEntityField.valueOf(stringToConvert.toUpperCase());
			}
			catch( IllegalArgumentException e ) {
				StringBuilder errStr = new StringBuilder( "GenomicDataType must be one of the following: ");				
				Iterator<GroceryEntityField> it = Arrays.asList ( GroceryEntityField.values() ).iterator();
				while( it.hasNext() ) {
					GroceryEntityField g = it.next();
					if( g != GroceryEntityUndefined && g != GroceryEntitInvalid ) {
						errStr.append(g.toString());
						errStr.append(",");
					}
				}
				
				IllegalArgumentException t = new IllegalArgumentException( errStr.toString(), e );
				throw t;				
			}
			return gdtRes;
		}
	 
	}


	List<GroceryEntity> findBySku(@Param("sku") int sku);
	List<GroceryEntity> findByDescription(@Param("description") String description);
	List<GroceryEntity> findByLastSold(@Param("lastSold") String lastSold);
	List<GroceryEntity> findByShelfLife(@Param("shelfLife") int shelfLife);
	List<GroceryEntity> findByDepartment(@Param("department") String department);
	List<GroceryEntity> findByPrice(@Param("price") float price);
	List<GroceryEntity> findByUnit(@Param("unit") int unit);
	List<GroceryEntity> findByXFor(@Param("xFor") int xFor);
	List<GroceryEntity> findByCost(@Param("cost") float cost);
	
	
	
}
