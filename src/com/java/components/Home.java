package com.java.components;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "HOME_TABLE")
@DynamicUpdate
public class Home  extends Product implements ProductCategory {

}
