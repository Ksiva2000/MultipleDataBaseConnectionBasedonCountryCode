package com.tanasvi.multidb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CombiningThreeTables {
	
	private String u_name;
	private String s_name;
	private String c_name;
	private String description;
	private float marks;
	private float price;
	private String c_order;
	
}
