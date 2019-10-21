package springdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springdemo.entity.Customer;
import springdemo.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {

        List<Customer> customers = customerService.getCustomers();

        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormUpdate")
    public String showFormUpdate(@RequestParam("customerId") int theId, Model model) {
        Customer customer = customerService.getCustomer(theId);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int theId) {
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }

    @PostMapping("/search")
    public String searchCustomers(@RequestParam("searchName") String searchName, Model model) {
        List<Customer> customers = customerService.searchCustomers(searchName);
        model.addAttribute("customers", customers);
        return "list-customers";
    }
}
