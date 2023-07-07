package com.customer.managment.customer_managment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import jakarta.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class customerController {
	
	 @Autowired
	    private CustomerRepository CustomeRepository;
        
	    //View all customer
	    @GetMapping("/customers")
	    public List < Customer > getAllCustomers() {
	        return CustomeRepository.findAll();
	    }
	    //, View a customer by id 
	    @GetMapping("/customers/{id}")
	    public ResponseEntity < Customer > getEmployeeById(@PathVariable(value = "id") Long Id)
	    throws ResourceNotFoundException {
	    	Customer Customer = CustomeRepository.findById(Id)
	            .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + Id));
	        return ResponseEntity.ok().body(Customer);
	    }
	    
	    
	    
	    @PostMapping( "/customers")
	    public Customer createCustomer(  @Valid @RequestBody Customer Customer) {
	        return CustomeRepository.save(Customer);
	    }
	   
	    
	    @PostMapping("/customers/bulk")
	    public ResponseEntity<String> bulkCreateCustomers(@RequestBody MultipartFile file) {
	        try {
	            Workbook workbook = WorkbookFactory.create(file.getInputStream());
	            Sheet sheet = workbook.getSheetAt(0);

	            List<Customer> customers = new ArrayList<>();

	            Iterator<Row> rowIterator = sheet.iterator();
	            // Skip the header row if needed
	            if (rowIterator.hasNext()) {
	                rowIterator.next();
	            }

	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                Customer customer = readCustomerFromRow(row);
	                customers.add(customer);
	            }

	            
	            CustomeRepository.saveAll(customers);

	            return ResponseEntity.ok("Bulk customer creation successful");
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Error processing the uploaded file");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	        }
	    }

	    private Customer readCustomerFromRow(Row row) {
	        // Implement your logic to extract customer data from the row
	       
	        String firstName = row.getCell(0).getStringCellValue();
	        String lastName = row.getCell(1).getStringCellValue();
	        String DOB = row.getCell(2).getStringCellValue();
	        String NIC = row.getCell(3).getStringCellValue();
	        String phone = row.getCell(4).getStringCellValue();
	        String Fmembers = row.getCell(5).getStringCellValue();
	        String address = row.getCell(5).getStringCellValue();
	        String city = row.getCell(6).getStringCellValue();
	        String country = row.getCell(7).getStringCellValue();
	        // ...

	        return new Customer(firstName, lastName,DOB,NIC,phone,Fmembers,address,city, country);
	    }

	   
	    
	    
	    @PutMapping("/customers/{id}")
	    public ResponseEntity < Customer > updateCustomer(@PathVariable(value = "id") Long Id,
	        @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
	    	Customer customer = CustomeRepository.findById(Id)
	            .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + Id));

	    	customer.setDOB(customerDetails.getDOB());
	    	customer.setLastName(customerDetails.getLastName());
	    	customer.setFirstName(customerDetails.getFirstName());
	    	customer.setNIC(customerDetails.getNIC());
	    	
	    	customer.setPhone(customerDetails.getPhone());
	    	customer.setFmembers(customerDetails.getFmembers());
	    	customer.setAddress(customerDetails.getAddress());
	    	
	    	customer.setCity(customerDetails.getCity());
	    	customer.setCountry(customerDetails.getCountry());
	        final Customer updatedCustomeer = CustomeRepository.save(customer);
	        return ResponseEntity.ok(updatedCustomeer);
	    }
	    
	    
	    @DeleteMapping("/customers/{id}")
	    public Map < String, Boolean > deleteCustomer(@PathVariable(value = "id") Long Id)
	    throws ResourceNotFoundException {
	    	Customer customer = CustomeRepository.findById(Id)
	            .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + Id));

	    	CustomeRepository.delete(customer);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }


}
