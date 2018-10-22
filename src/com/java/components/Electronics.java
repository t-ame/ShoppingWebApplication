package com.java.components;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "ELECTRONICS_TABLE")
@DynamicUpdate
public class Electronics extends Product implements ProductCategory {

	

}
