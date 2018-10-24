package com.java.components;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "OUTDOORS_TABLE")
@DynamicUpdate
public class Outdoors  extends Product implements ProductCategory {

}
