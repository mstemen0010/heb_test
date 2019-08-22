package demo.controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import demo.repository.GroceryEntityRepository;
import demo.model.GroceryEntity;
//

@Configuration
@PropertySource("classpath:foo.properties")
@ConfigurationProperties(prefix = "mail")
 class ConfigProperties {
	 
    public static class Credentials {
        private String authMethod;
        private String username;
        private String password;
 
       // standard getters and setters
    }
    private String host;
    private int port;
    private String from;
    private Credentials credentials;
    private List<String> defaultRecipients;
    private Map<String, String> additionalHeaders;
    
	private int getPort() {
		return port;
	}
	private void setPort(int port) {
		this.port = port;
	}
	private String getHost() {
		return host;
	}
	private void setHost(String host) {
		this.host = host;
	}
  
    // standard getters and setters
}

@RestController
@RequestMapping("/api/items")
public class ItemController {
//
	boolean clearH2 = false;
		
	@Autowired
    private GroceryEntityRepository groceryEntityRepository;
    Map<Long, GroceryEntity> groceryItemsMap = new HashMap<Long, GroceryEntity>();

    @Value("${spring.application.name}")
    String appName;
    
    @Value("${mail.host}`")
    String homeName;
 
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        
        return "index";
    }
 
	@PostConstruct
    public void initIt() throws Exception {
		if( clearH2 ) {
			groceryEntityRepository.deleteAll();
		}
		
		if(groceryEntityRepository.count() == 0 ) {
			create( new GroceryEntity(753542, "banana", "9/5/2017", 4, "Produce", 2.99f, -1, 1, 0.44f));
			create( new GroceryEntity(321654, "apples", "9/6/2017", 7, "Produce", 1.49f, -1, 1, 0.20f));
			create( new GroceryEntity(95175, "strawberry", "9/7/2017", 3, "Produce", 2.49f, -1, 1, 0.10f));
			create( new GroceryEntity(321753, "onion", "9/8/2017", 9, "Produce", 1.49f, 1, 1, 0.05f));
			create( new GroceryEntity(987654, "Tomato", "9/9/2017", 4, "Produce", 2.49f, -1, 1, 0.20f));
			create( new GroceryEntity(11122, "grapes", "9/10/2017", 7, "Produce", 2.49f, -1, 1, 1.20f));
			create( new GroceryEntity(124872, "Lettuce", "9/11/2017", 5, "Produce", 0.79f, -1, 1, 0.10f));
			create( new GroceryEntity(113, "bread", "9/12/2017", 14, "Grocery", 1.50f, 1, 1, 0.12f));
			create( new GroceryEntity(1189, "hamburger buns", "9/13/2017", 14, "Grocery", 1.75f, 1, 1, 0.14f));
			create( new GroceryEntity(12221, "pasta sauce", "9/14/2017", 23, "Grocery", 3.75f, 1, 1, 1.00f));
			create( new GroceryEntity(1111,"cheese slices","9/15/2017",20,"Grocery", 2.68f ,1,1, 0.40f ));
			create( new GroceryEntity(18939,"sliced turkey","9/16/2017",20,"Grocery", 3.29f ,1,1, 0.67f ));
			create( new GroceryEntity(90879,"tortilla chips","9/17/2017",45,"Grocery", 1.99f ,1,1, 0.14f ));
			create( new GroceryEntity(119290,"cereal","9/18/2017",40,"Grocery", 3.19f ,1,1, 0.19f ));
			create( new GroceryEntity(4629464,"canned vegtables","9/19/2017",200,"Grocery", 1.89f ,1,1, 0.19f ));
			create( new GroceryEntity(9000001,"headache medicine","9/20/2017",365,"Pharmacy", 4.89f ,1,1, 1.90f ));
			create( new GroceryEntity(9000002,"Migraine Medicine","9/21/2017",365,"Pharmacy", 5.89f ,1,1, 1.90f ));
			create( new GroceryEntity(9000003,"Cold and Flu","9/22/2017",365,"Pharmacy", 3.29f ,1,1, 1.90f ));
			create( new GroceryEntity(9000004,"Allegry Medicine","9/23/2017",365,"Pharmacy", 3.00f ,1,1, 1.25f ));
			create( new GroceryEntity(9000005,"Pain","9/24/2017",365,"Pharmacy", 2.89f ,1,1, 1.00f ));
		}
	}
    
    @Autowired
    public ItemController(GroceryEntityRepository repository) {
        this.groceryEntityRepository = repository;
    }
    
	
	@GetMapping("/sku/{sku}")
    public List<GroceryEntity> findBySku(@PathVariable int sku) {
    	return groceryEntityRepository.findBySku(sku);
    }
	
	@GetMapping("/description/{description}")
    public List<GroceryEntity> findByDescription(@PathVariable String description) {
    	return groceryEntityRepository.findByDescription(description);
    }
	
	@GetMapping("/lastSold/{lastSold}")
    public List<GroceryEntity> findByLastSold(@PathVariable String lastSold) {
    	return groceryEntityRepository.findByLastSold(lastSold);
    }
	
	@GetMapping("/shelfLife/{shelfLife}")
    public List<GroceryEntity> findByShelfLife(@PathVariable int shelfLife) {
    	return groceryEntityRepository.findByShelfLife(shelfLife);
    }
	
	@GetMapping("/department/{department}")
    public List<GroceryEntity> findByDepartment(@PathVariable String department) {
    	return groceryEntityRepository.findByDepartment(department);
    }
	
	@GetMapping("/price/{price}")
    public List<GroceryEntity> findByPrice(@PathVariable float price) {
    	return groceryEntityRepository.findByPrice(price);
    }
	
	@GetMapping("/unit/{unit}")
    public List<GroceryEntity> findByUnit(@PathVariable int unit) {
    	return groceryEntityRepository.findByUnit(unit);
    }
	
	@GetMapping("/xFor/{xFor}")
    public List<GroceryEntity> findByXFor(@PathVariable int xFor) {
    	return groceryEntityRepository.findByXFor(xFor);
    }
	
	@GetMapping("/cost/{cost}")
    public List<GroceryEntity> findByCost(@PathVariable float cost) {
    	return groceryEntityRepository.findByCost(cost);
    }
	
    @GetMapping(value = "/all")
	public List<GroceryEntity> getResource() {
		
		return toList( this.groceryEntityRepository.findAll()); // groceryItemsMap.entrySet().stream()
	}
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroceryEntity create(@RequestBody GroceryEntity entity) {
        return groceryEntityRepository.save(entity);
    }
    
    public static <E> List<E> toList(Iterable<E> iterable) {
        if(iterable instanceof List) {
          return (List<E>) iterable;
        }
        ArrayList<E> list = new ArrayList<E>();
        if(iterable != null) {
          for(E e: iterable) {
            list.add(e);
          }
        }
        return list;
      }

}
