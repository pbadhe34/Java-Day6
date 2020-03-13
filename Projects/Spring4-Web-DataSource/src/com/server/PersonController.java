package com.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.server.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
    private PersonService personService;

@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPersonView() {

		ModelAndView modelView = new ModelAndView("personPage");		 
		modelView.addObject("person", new Person());		 
		return modelView;
	}
	 	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	  public String addPerson(@RequestParam Map<String,String> requestParams) {
        System.out.println("PersonController.addPerson()");      
         
        String id = requestParams.get("id");
        Person  person = new Person();
        person.setCountry(requestParams.get("country"));
       // person.setId(Integer.parseInt(id));
        person.setName(requestParams.get("name"));
        System.out.println("The person details are  "+person);
        personService.addPerson(person);
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getAllPersons")
	public ModelAndView getAll() {
		ModelAndView modelView = new ModelAndView("personPage");
		List list  = personService.listPersons();
		modelView.addObject("listPersons", list);		 
		return modelView;
	}
	
    /*public @ResponseBody List<Person> getAll(){
     return personService.listPersons();
    }*/
}