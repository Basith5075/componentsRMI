package pdfcomp;

import java.rmi.Naming;

import arithmetic.Arithmetic;
import componentstore.Stores;
import smscomponent.SMS;

public class Client {

	public static void main(String[] args) throws Exception {
		Stores store = (Stores)Naming.lookup("rmi://localhost:1999/evestore");
		SMS sms = (SMS)store.getEveStoreObject();
		sms.createSMS();
		PDF pdf = (PDF)store.getEveStoreObject();
		pdf.createPDF();
		Arithmetic ar = (Arithmetic)store.getEveStoreObject();
		ar.calculation();
		System.out.println("---------------------------------------\n******* Multiplying *********");
		ar = (Arithmetic)store.getEveStoreObject();
		ar.calculation();
		
	}
}
