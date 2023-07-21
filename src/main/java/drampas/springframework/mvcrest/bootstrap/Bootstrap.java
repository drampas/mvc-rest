package drampas.springframework.mvcrest.bootstrap;

import drampas.springframework.mvcrest.domain.Category;
import drampas.springframework.mvcrest.domain.Customer;
import drampas.springframework.mvcrest.repositories.CategoryRepository;
import drampas.springframework.mvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);


        System.out.println("Categories loaded = " + categoryRepository.count() );
        loadCustomers();
    }
    private void loadCustomers(){
        Customer customer1=new Customer();
        customer1.setFirstName("Maria");
        customer1.setFirstName("Bezos");
        Customer customer2=new Customer();
        customer2.setFirstName("George");
        customer2.setLastName("Long");
        Customer customer3=new Customer();
        customer3.setFirstName("Anna");
        customer3.setLastName("Clint");
        Customer customer4=new Customer();
        customer4.setFirstName("John");
        customer4.setLastName("Doe");
        Customer customer5=new Customer();
        customer5.setFirstName("Hellen");
        customer5.setLastName("DeWalt");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);

        System.out.println("Customers loaded:"+customerRepository.count());
    }
}
