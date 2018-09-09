package ru.stqa.island.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.island.addressbook.model.ContactData;
import ru.stqa.island.addressbook.model.Contacts;
import ru.stqa.island.addressbook.model.GroupData;
import ru.stqa.island.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public int getContactLastId(ContactData contact){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where firstname = '" + contact.getFirstname() +
                "' and lastname = '" + contact.getLastname() + "' and address = '" + contact.getAddress() +
                "' and email = '" + contact.getEmail() + "' and phone = '" + contact.getHomePhone() + "'", ContactData.class).getResultList();
        session.getTransaction().commit();
        session.close();
        int maxId = result.get(0).getId();
        for (int i = 0; i < result.size(); i++){
            if (result.get(i).getId() > maxId){
                maxId = result.get(i).getId();
            }
        }
        return maxId;
    }
}
