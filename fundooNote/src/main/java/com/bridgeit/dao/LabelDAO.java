package com.bridgeit.dao;

import java.util.List;

import com.bridgeit.model.Label;

public interface LabelDAO {

	boolean addlabel(Label label);

	List<Label> getLabels(int id);

	boolean updateLabel(Label label);

	Label getLabelById(int labelId);

	boolean deleteLabel(Label label);

}
