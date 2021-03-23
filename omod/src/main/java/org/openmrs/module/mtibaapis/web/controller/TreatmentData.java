package org.openmrs.module.mtibaapis.web.controller;

import java.util.List;

public class TreatmentData {
	
	private Treatment treatment;
	
	private AccountHolder accountHolder;
	
	private Patient patient;
	
	private Provider provider;
	
	public Treatment getTreatment() {
		return treatment;
	}
	
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}
	
	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	private class Treatment {
		
		String code;
		
		String date;
		
		String policyHolderRef;
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getDate() {
			return date;
		}
		
		public void setDate(String date) {
			this.date = date;
		}
		
		public String getPolicyHolderRef() {
			return policyHolderRef;
		}
		
		public void setPolicyHolderRef(String policyHolderRef) {
			this.policyHolderRef = policyHolderRef;
		}
		
	}
	
	private class AccountHolder extends Person {
		
		Account account;
		
		private class Account {
			
			String program;
			
			String description;
			
			String status;
			
			Limit limit;
			
			public String getProgram() {
				return program;
			}
			
			public void setProgram(String program) {
				this.program = program;
			}
			
			public String getDescription() {
				return description;
			}
			
			public void setDescription(String description) {
				this.description = description;
			}
			
			public String getStatus() {
				return status;
			}
			
			public void setStatus(String status) {
				this.status = status;
			}
			
			public Limit getLimit() {
				return limit;
			}
			
			public void setLimit(Limit limit) {
				this.limit = limit;
			}
		}
	}
	
	private class Patient extends Person {
		
		String membershipNumber;
		
		public String getMembershipNumber() {
			return membershipNumber;
		}
		
		public void setMembershipNumber(String membershipNumber) {
			this.membershipNumber = membershipNumber;
		}
	}
	
	private class Provider {}
	
	private class Limit {
		
		String currency;
		
		Float amount;
		
		public String getCurrency() {
			return currency;
		}
		
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		
		public Float getAmount() {
			return amount;
		}
		
		public void setAmount(Float amount) {
			this.amount = amount;
		}
	}
	
	private abstract class Person {
		
		String fullName;
		
		String firstName;
		
		String middleName;
		
		String lastName;
		
		String dateOfBirth;
		
		String gender;
		
		String mobileNumber;
		
		String relationship;
		
		List<Identification> identifications;
		
		public String getFullName() {
			return fullName;
		}
		
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		
		public String getFirstName() {
			return firstName;
		}
		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public String getMiddleName() {
			return middleName;
		}
		
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public String getDateOfBirth() {
			return dateOfBirth;
		}
		
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		
		public String getGender() {
			return gender;
		}
		
		public void setGender(String gender) {
			this.gender = gender;
		}
		
		public String getMobileNumber() {
			return mobileNumber;
		}
		
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		
		public String getRelationship() {
			return relationship;
		}
		
		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}
		
		public List<Identification> getIdentifications() {
			return identifications;
		}
		
		public void setIdentifications(List<Identification> identifications) {
			this.identifications = identifications;
		}
		
		private class Identification {
			
			String type;
			
			String number;
			
			public String getType() {
				return type;
			}
			
			public void setType(String type) {
				this.type = type;
			}
			
			public String getNumber() {
				return number;
			}
			
			public void setNumber(String number) {
				this.number = number;
			}
		}
	}
}
