package com.bridgeit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.Label;
@Repository
public class LabelDaoImpl implements LabelDAO {


	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public boolean addlabel(Label label) {
		// TODO Auto-generated method stub
		System.out.println("dao"+label);
		getCurrentSession().save(label);
		
		return true;
	}

}
