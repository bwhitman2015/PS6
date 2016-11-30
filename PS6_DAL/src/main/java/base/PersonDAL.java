package base;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import util.HibernateUtil;
//changes for pushhhhhhh

public class PersonDAL {
	
	public static PersonDomainModel addPerson(PersonDomainModel prsn){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int employeeID = 0;
		try {
			tx = session.beginTransaction();
			session.save(prsn);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return prsn;
		
		
	}
	
	public static ArrayList<PersonDomainModel> getPersons() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel prsnGet = null;		
		ArrayList<PersonDomainModel> ppl = new ArrayList<PersonDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List persons = session.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = persons.iterator(); iterator.hasNext();) {
				PersonDomainModel prsn = (PersonDomainModel) iterator.next();
				ppl.add(prsn);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ppl;
	}
	
	public static PersonDomainModel getPerson(UUID prsnID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel prsnGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			Query query = session.createQuery("from PersonDomainModel where personId = :id ");
			query.setParameter("id", prsnID.toString());
			
			List<?> list = query.list();
			prsnGet = (PersonDomainModel)list.get(0);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return prsnGet;
		
	}
	
	public static void deletePerson(UUID prsnID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel prsnGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			PersonDomainModel prsn = (PersonDomainModel) session.get(PersonDomainModel.class, prsnID);
			session.delete(prsn);
		
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	public static PersonDomainModel updatePerson(PersonDomainModel prsn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel prsnGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			session.update(prsn);
	
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return prsn;
	}		
	
	
	
		
		
	
	

}
