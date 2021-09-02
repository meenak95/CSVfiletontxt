package net.meena.spring.batch;

public class SimCardDTO {

	    private String MSISDN;
	    private String simType;
	    private String name;
	    private String dateOfBirth;
	    private String gender;
	    private String address;
	    private String idNumber;
	    
		public String getMSISDN() {
			return MSISDN;
		}
		public void setMSISDN(String mSISDN) {
			MSISDN = mSISDN;
		}
		public String getSimType() {
			return simType;
		}
		public void setSimType(String simType) {
			this.simType = simType;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getIdNumber() {
			return idNumber;
		}
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}
		@Override
		    public String toString() {
		        return "SimCardDTO{" +
		        		 "MSISDN='" + MSISDN + '\'' +
		        		  "simType='" + simType + '\'' +
		        		   "name='" + name + '\'' +
		        		    "dateOfBirth='" + dateOfBirth + '\'' +
		                "gender='" + gender + '\'' +
		                ", address='" + address + '\'' +
		                ", idNumber='" + idNumber + '\'' +
		                '}';
		    }
}
