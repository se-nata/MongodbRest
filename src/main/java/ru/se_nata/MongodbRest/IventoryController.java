package ru.se_nata.MongodbRest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IventoryController {
	@Autowired
	private InventoryRepository inventoryRepository;

	public IventoryController(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	@GetMapping("/inventory/")
	public List<Inventory> allInvetory(){
		return inventoryRepository.findAll();
	}
	
	@GetMapping("/inventory/{id}")	
	public ResponseEntity<Inventory> getoneInventory(@PathVariable ("id") String id){
		Inventory inventory=inventoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Inventory not found for this id "));
		return ResponseEntity.ok(inventory);
	}
	@PostMapping("/inventory/")
	public ResponseEntity<Inventory> newInventory(@RequestBody Inventory newInventory){
		inventoryRepository.save(newInventory);
		return ResponseEntity.ok().build();
	}
	@PutMapping("/inventory/{id}")
	public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory newinventory,@PathVariable ("id") String id) {
		Inventory inventupdate=inventoryRepository.findById(id).map(inventory->{
			inventory.setItem(newinventory.getItem());
			inventory.setQty(newinventory.getQty());
			inventory.setSize(newinventory.getSize());
			inventory.setStatus(newinventory.getStatus());
			return inventoryRepository.save(inventory);
		}).orElseGet(()->{
		newinventory.setId(id);
		return inventoryRepository.save(newinventory);});
		return ResponseEntity.ok().body(inventupdate);
	}
	@DeleteMapping("/inventory/{id}")
	public Map <String,Boolean> delete(@PathVariable String id) {
	Inventory inventory=inventoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Inventory not found for this id "));
	inventoryRepository.delete(inventory);
	Map <String,Boolean> respons=new HashMap<>();
		 respons.put("delete", Boolean.TRUE);
		return respons;
	}
		
}
