package com.java.components;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "BOOKS_TABLE")
@DynamicUpdate
public class Books  extends Product implements ProductCategory {

}
