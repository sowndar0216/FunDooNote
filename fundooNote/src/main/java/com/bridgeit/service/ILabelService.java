package com.bridgeit.service;

import java.util.List;

import com.bridgeit.model.Label;
import com.bridgeit.model.Note;

public interface ILabelService {

	boolean addLabel(Label label);

	List<Label> getLabel(String token);

}
