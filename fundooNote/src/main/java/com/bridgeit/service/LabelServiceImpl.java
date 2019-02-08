package com.bridgeit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.LabelDAO;
import com.bridgeit.model.Label;
import com.bridgeit.model.Note;
import com.bridgeit.utility.UserToken;

@Service
@Transactional
public class LabelServiceImpl implements ILabelService {

	@Autowired
	private LabelDAO labelDAO;

	@Override
	public boolean addLabel(Label label) {

		System.out.println("LIST of NOTE" + label.getNoteList());

		boolean check = labelDAO.addlabel(label);

		return true;
	}

	@Override
	public List<Label> getLabel(String token) {

		try {
			int id = UserToken.tokenVerify(token);
			System.out.println("token   " + token);
			List<Label> listAll = labelDAO.getLabels(id);
			System.out.println("list of LABEL   " + listAll);
			return listAll;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateLabel(Label label, String token) {
		
		try {
			int id = UserToken.tokenVerify(token);
			
			if(id==label.getUserId()) {
				
				return labelDAO.updateLabel(label);
				
				
				
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public Label getLabelById(int labelId) {
		
		return labelDAO.getLabelById(labelId);
	}

	@Override
	public boolean userVerify(Label label, String token) {
		try {
			int id = UserToken.tokenVerify(token);
			if(id==label.getUserId()) {
				
				
				return labelDAO.deleteLabel(label);
				
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

}
