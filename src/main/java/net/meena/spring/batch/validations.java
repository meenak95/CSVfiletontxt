package net.meena.spring.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validations {

	public String validateRegistrationParameters(SimCardDTO simcarddto) {
		// TODO Auto-generated method stub
    
    String  simvalidate=validateSIM_TYPE(simcarddto.getSimType());
    String  namevalidate=validateName(simcarddto.getName());
    String  dobvalidate=validateDOB(simcarddto.getDateOfBirth());
    String  gendervalidate=validateGender(simcarddto.getGender());
    String  idvalidate= validateID_Number(simcarddto.getIdNumber());
    String  addressvalidate= validateAddress(simcarddto.getAddress());
    String validateMSISDN=validateMSISDN(simcarddto.getMSISDN());
    
    if(validateMSISDN.equals("")  &&simvalidate.equals("") && namevalidate.equals("") && dobvalidate.equals("") && gendervalidate.equals("") && idvalidate.equals("") && addressvalidate.equals("")) {
       return "";
	}
	else {
		return simcarddto.getMSISDN()+"- not registred because of following reasons  "+validateMSISDN+simvalidate+ namevalidate+dobvalidate+gendervalidate+idvalidate+addressvalidate;
	}
	
 }
	public static String validateMSISDN(String testMSISDN) {
		
		String b="";
		if((!testMSISDN.equals("")) && (testMSISDN!= null)) {
		String CC = "66";
		String numberLength = "8";
		String finalMSISDN = null;

		Pattern msisdnWithCC= Pattern.compile("^("+CC+")([0-9]{"+numberLength+"})$");
		Pattern msisdnWithoutCC= Pattern.compile("^(0)([0-9]{"+numberLength+"})$");
		Pattern msisdnWithoutZero= Pattern.compile("^([0-9]{"+numberLength+"})$");

		Matcher teststring= msisdnWithCC.matcher(testMSISDN);

		if(teststring.matches())
		{
		finalMSISDN = testMSISDN;
		}else{
		teststring = msisdnWithoutCC.matcher(testMSISDN);
		if(teststring.matches()){
		finalMSISDN = CC+teststring.group(2);
		}else{
		teststring = msisdnWithoutZero.matcher(testMSISDN);
		if(teststring.matches()){
		finalMSISDN = CC+teststring.group(1);
		}
		}
		}

		if(finalMSISDN == null)
		{
			 b="MSISDN is INVALID, Please enter a valid MSISDN";
		}
		}
		else {
			 b="MSISDN should not be empty, Please enter a MSISDN";
		}
		return b;
}

	public static String validateSIM_TYPE(String SIM_Type) {
		
		String b="";
		
		if ((!SIM_Type.equals("")) && (SIM_Type != null)) {

			if (!SIM_Type.equalsIgnoreCase("PREPAID") && !SIM_Type.equalsIgnoreCase("POSTPAID")) {
               b="Selected SIM type is not valid," + " Please Specify POSTPAID or PREPAID only";
			}
		} else {
			  b="Please enter a SIM type (POSTPAID or PREPAID)";
		}
       return b;
	}

	public static String validateName(String name) {
		String b="";
		if ((!name.equals("")) && (name != null)) {
			if ((!name.matches("^[a-zA-Z0-9]*$"))) {
				  
				   b="Name is not valid, Name should not contain any  " + "special characters ";
			}
		} else {
			  
			b="Please enter your Name, Name cannot be empty";
		}
		 return b;
	}

	public static String validateDOB(String date) {
		
		String b="";
		if ((!date.equals("")) && (date != null)) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

			Date inputDate = null;
			try {
				inputDate = (Date) formatter.parse(date);
			} catch (ParseException e) {
				System.out.println("parse exception arised");
				e.printStackTrace();
			}
			System.out.println("inputDate = " + inputDate);

			Date dateNow = new Date();
			// System.out.println("dateNow = "+dateNow);

			String currentdate1 = formatter.format(dateNow);
			// System.out.println("string currentdate ="+currentdate1);

			Date currentdate = null;
			try {
				currentdate = (Date) formatter.parse(currentdate1);
			} catch (ParseException e) {
				System.out.println("parse exception arised");
				e.printStackTrace();
			}
			System.out.println("currentdate = " + currentdate);

			if (!inputDate.before(currentdate)) {
				
				b="Date Of Birth is not valid, Please Enter a date before today";
			}

		} else {
			
			b="Date of Birth should not be empty, Please enter your Date of Birth";
		}
		 return b;
	}

	public static String validateGender(String gender) {
		String b="";
		if ((!gender.equals("")) && (gender != null)) {
			if (!gender.equalsIgnoreCase("F") && !gender.equalsIgnoreCase("M")) {
				 
				b="Please enter your gender in 'F' or 'M' format";
			}
		} else {
			b="Gender should not be empty, " + "Please enter your Gender";
		}
		return b;
	}

	public static String validateAddress(String address) {
		String b="";
		if ((!address.equals("")) && (address != null)) {
			if (address.length()<20) {
				 
				b="Please enter your full address" + " (Address must be more than 20 characters)";
			}
		} else {
			
               b="Address should not be empty, " + "Please enter your Address";
		}
		return b;
	}

	public static String validateID_Number(String id_number) {
		String b="";
		if ((!id_number.equals("")) && (id_number != null)) {
			if (!id_number.matches("^[a-zA-Z0-9]*$")) {
				b="ID_Number is not valid, ID_Number should be a " + "combination of characters and numbers";
			}
		} else {
			 
			b="Please enter an ID_Number, ID_Number cannot be empty";
		}
		return b;
	}

	public static Boolean validateMSISDN1(String id_number) {
		Boolean b=true;
		return b;
	}

}
