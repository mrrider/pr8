package com.kozak;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.googlecode.ehcache.annotations.Cacheable;

@Transactional
public class WorkWithStudents {
	@Autowired
	private StudentsDao studentsDao;

	@Autowired
	private TransactionTemplate transactionTemplate;

	public Student saveStudentToDb(final Student student) {
		transactionTemplate.execute(new TransactionCallback<Void>() {
			public Void doInTransaction(TransactionStatus txStatus) {
				try {
					studentsDao.addStudent(student);
					System.out.println("Student has been added " + student);
				} catch (RuntimeException e) {
					txStatus.setRollbackOnly();
					throw e;
				}
				return null;
			}
		});
		return student;
	}
}
