package com.mc.customer.servicing.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name= "customer_details")
public class Customer {
	
    @javax.persistence.Id
	private String Id;
	
    private String name;
	
    private String phone;
	
    private String phone_extn;
	
    private String status;
}
