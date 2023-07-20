package ru.se_nata.MongodbRest;


import org.springframework.data.mongodb.repository.MongoRepository;
;


public interface InventoryRepository extends MongoRepository<Inventory, String>{

	 
}
