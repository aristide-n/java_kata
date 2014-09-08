package com.aristideniyungeko;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Defines a contact.
 */
@Entity
@Table(name="CONTACTS")
public class Contact {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   @Column private String name;
   @Column private String address;
   @Column private String gender;
   @Column private String dob;
   @Column private String email;
   @Column private String mobile;
   @Column private String phone;

   @Override
   public String toString() {
      return ToStringBuilder.reflectionToString(this);
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getDob() {
      return dob;
   }

   public void setDob(String dob) {
      this.dob = dob;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }
}