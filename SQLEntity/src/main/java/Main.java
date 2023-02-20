import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Subscription subscription = session.get(Subscription.class, new Key(1, 2));
        PurchaseList purchaseList = session.get(PurchaseList.class, new PurchaseList.Key("Фуриков Эрнст",
                "Мобильный разработчик с нуля"));
        System.out.println("Студент " + subscription.getStudentId().getName() +
                " подписан на курс - " + subscription.getCourseId().getName() +
                ", стоимость курса " + purchaseList.getPrice());


        sessionFactory.close();
    }

}

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction(); //открываем транзакцию

        CriteriaBuilder builder = session.getCriteriaBuilder(); //строит объекты запросов

        CriteriaQuery<Purchaselist> query = builder.createQuery(Purchaselist.class); //возвращает запрос, указывает тип данных
        Root<Purchaselist> root = query.from(Purchaselist.class); //корневой объект, от которого производится обход дерева
        query.select(root);
        List<Purchaselist> purchaselist = session.createQuery(query).getResultList();

        for (Purchaselist p : purchaselist) {
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();

            String courseName = p.getCourseName();
            String studentName = p.getStudentName();
            int studentId = p.getStudent().getId();
            int courseId = p.getCourse().getId();

            linkedPurchaseList.setId(new LPKey(studentName,courseName));
            linkedPurchaseList.setStudentId(studentId);
            linkedPurchaseList.setCourseId(courseId);
            linkedPurchaseList.setStudentName(studentName);
            linkedPurchaseList.setCourseName(courseName);
            linkedPurchaseList.setPrice(p.getPrice());
            linkedPurchaseList.setSubDate(p.getSubDate());
            session.saveOrUpdate(linkedPurchaseList);
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}