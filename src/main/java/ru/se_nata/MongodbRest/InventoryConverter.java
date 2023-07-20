package ru.se_nata.MongodbRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InventoryConverter implements Converter<String, Inventory>{
	  @Autowired
	  private InventoryRepository  inventoryRepository;

	  public InventoryConverter(InventoryRepository inventoryRepository) {
		super();
		this.inventoryRepository = inventoryRepository;
	}
      @Override
	  public Inventory convert(String id) {
	    return inventoryRepository.findById(id).orElse(null);
	  }
}
