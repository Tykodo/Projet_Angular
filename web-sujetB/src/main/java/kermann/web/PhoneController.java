package kermann.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kermann.web.model.Phone;


@RestController
public class PhoneController {

	private List<Phone> phones = new ArrayList<Phone>();
	
	@PostConstruct
	public void intialize(){
		phones = new ArrayList<Phone>();
		
		Phone phone = new Phone();
		phone.setName("Galaxy S7");
		phone.setMaker("Samsung");
		phone.setOs("Android");
		phone.setPrice(699.90);
		phone.setId(1);
		phones.add(phone);
		
		Phone phone2 = new Phone();
		phone2.setName("Galaxy Note");
		phone2.setMaker("Samsung");
		phone2.setOs("Android");
		phone2.setPrice(699.90);
		phone2.setId(2);
		phones.add(phone2);

	}
	
	@GetMapping("/phones")
	public List<Phone> getPhones(){
		return phones;
	}
	
	private boolean checkID(int id){
		boolean result = true;
		for (int i=0; i<phones.size();i++){
			if (id == phones.get(i).getId())
				result = false;
		}
		return result;
	}
	
	@PostMapping("/phones/addPhone")
	public void addPhone(@RequestBody Phone phone){
		Random rand = new Random();
		int id = rand.nextInt();
		if (checkID(id)){
			phone.setId(id);
			phones.add(phone);
		}
		else addPhone(phone);
	}
	
	@PostMapping("/phones/remove/{id}")
	public void removePhone(@PathVariable Integer id){
		Phone match = null;
		for(Phone phone : phones){
			if(phone.getId() == id)
				match = phone;
		}
		if (match != null)
			phones.remove(match);
	}
	
	@PostMapping("/phones/edit/{id}")
	public void editPhone(@PathVariable Integer id, @RequestBody Phone phone){
		for(Phone p : phones){
			if(p.getId() == id){
				p.setName(phone.getName());
				p.setMaker(phone.getMaker());
				p.setOs(phone.getOs());
				p.setPrice(phone.getPrice());
			}
		}
		
	}

}
