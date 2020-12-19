package com.lhs.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lhs.model.TestTraineeEmpMast;
import com.lhs.model.TestTraineeUserMast;

@Repository
public class EmployeeRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean findByUsernameAndPassword(String username, String password) {

		// Transaction transaction = null;
		// TestTraineeUserMast testTraineeUserMast = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "from TestTraineeUserMast where user_name='" + username + "' and password='" + password + "'";
			Query query = session.createQuery(sql);
			List<TestTraineeUserMast> list = query.list();
			if (list.size() > 0 && list !=null) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return false;

	}

	@SuppressWarnings("unchecked")
	public List<TestTraineeEmpMast> showAllEmployee() {
		System.out.println("showAllEmloyee Called");
		Transaction transaction = null;
		Session session = null;
		List<TestTraineeEmpMast> list = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			list = session.createQuery("from TestTraineeEmpMast").list();
			// list = (List<TestTraineeEmpMast>) query.list();
			System.out.println(list);
			transaction.commit();
			System.out.println("fetch data from database is successfully ");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();

			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	public void saveUser(TestTraineeEmpMast testTraineeEmpMast) {
		Transaction transaction = null;
		try {
			Session session = sessionFactory.openSession();
			transaction = session.beginTransaction(); // start a new transaction
			System.out.println("object of test" + testTraineeEmpMast);
			session.save(testTraineeEmpMast);
			transaction.commit(); // commit transaction
		} catch (Exception ex) {
			System.err.println("Error -->" + ex.getMessage());
			if (transaction != null) {
				transaction.rollback(); // rollback transaction on exception
			}
		}
	}

	public TestTraineeEmpMast findOneEmployee(int id) {
		Transaction transaction = null;
		TestTraineeEmpMast testTraineeEmpMast = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			testTraineeEmpMast = (TestTraineeEmpMast) session.get(TestTraineeEmpMast.class, id);
			transaction.commit();
			System.out.println("select data from user is successfully ");
			return testTraineeEmpMast;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void updateUser(TestTraineeEmpMast testTraineeEmpMast) {
		try (Session session = sessionFactory.openSession()) {
			System.out.println("update query called" + testTraineeEmpMast.getEMP_NAME());
			Transaction transaction = null;
			transaction = session.beginTransaction();
			session.saveOrUpdate(testTraineeEmpMast);
			transaction.commit();
			System.out.println("Rows affected: " + testTraineeEmpMast.getEMP_NAME());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<TestTraineeEmpMast> showFilter(String code, String ename, String mobile, String reg_date) {
		System.out.println("called Filter");
		Session session = sessionFactory.openSession();
		String hql = "from TestTraineeEmpMast where 1=1";

		if (ename != null && ename != "") {
			hql += " AND EMP_NAME='" + ename + "'";
		}
		if (code != null && code != "") {
			hql += " AND DESIG_CODE='" + code + "'";
		}
		if (mobile != null && mobile != "") {
			hql += " AND MOBILE='" + mobile + "'";
		}
		if (reg_date != null && reg_date != "") {
			hql += "AND trunc(REG_DATE)=to_date('" + reg_date + "','dd/MM/yyyy')";
		}
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<TestTraineeEmpMast> list = (ArrayList<TestTraineeEmpMast>) query.list();
		System.out.println("Successfully Called");
		return list;
	}

	public void deleteEmployee(int id) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			System.out.println(id);
			// Delete a student object
			TestTraineeEmpMast testTraineeEmpMast = session.get(TestTraineeEmpMast.class, id);
			if (testTraineeEmpMast != null) {
				session.delete(testTraineeEmpMast);
			}
			System.out.println(testTraineeEmpMast.getEMP_NAME() + " is deleted successfully");
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

}
