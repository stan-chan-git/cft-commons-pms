package cft.commons.pms.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class EasyuiApplication implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1224793135752589117L;

	private String id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
}
