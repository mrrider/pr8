package com.kozak;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Transactional
public class WorkWithStudents {
	@Autowired
	private StudentsDao studentsDao;

	@Autowired
	private TransactionTemplate transactionTemplate;

	public Student update(final Student student) {
		Student createdStudent = null;
		if (student != null) {
			final javax.persistence.EntityManagerFactory entityManagerFactory = com.kozak.EntityManagerFactory.getEntityManagerFactory();
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			try {
				entityManager.getTransaction().begin();
				Student createdEntity = entityManager.merge(student);
				entityManager.getTransaction().commit();
			} catch (Exception ex) {
				entityManager.getTransaction().rollback();
			} finally {
				entityManager.close();
			}
		}
		return createdStudent;
	}
}
