package springdemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springdemo.entity.Customer;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> query =
                currentSession.createQuery(
                        "from Customer order by lastName",
                        Customer.class);

        return query.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Customer.class, theId);
    }

    @Override
    public void deleteCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery(
                "delete from Customer where id=:customerId");
        query.setParameter("customerId", theId);
        query.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = null;

        if (searchName != null && searchName.trim().length() > 0) {
            query = currentSession.createQuery(
                    "from Customer where lower(firstName) like :theName or " +
                            "lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        } else {
            query = currentSession.createQuery("from Customer", Customer.class);
        }

        return (List<Customer>) query.getResultList();
    }
}
