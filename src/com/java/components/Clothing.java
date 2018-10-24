package com.java.components;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "CLOTHING_TABLE")
@DynamicUpdate
public class Clothing  extends Product implements ProductCategory {

}
