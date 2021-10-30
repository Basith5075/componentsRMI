package componentstore;

import java.io.FileInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Properties;

import pdfcomp.PDF;
import pdfcomp.PDFComponent;
import smscomponent.SMS;
import smscomponent.SMSComponent;

public class StoresServer extends UnicastRemoteObject implements Stores {
	
	public StoresServer() throws RemoteException {
		// TODO Auto-generated constructor stub
	}
//	@Override
//	public Object getEveStoreObject() throws RemoteException {
//	
//		Object obj = Proxy.newProxyInstance(new MyApp().getClass().getClassLoader(),
//				new Class[] {PDF.class, SMS.class},
//				new MyInvocationHandler(new Object[] {new PDFComponent(), new SMSComponent()}));
//		return obj;
//		
//	}
//	@Override
//	public Object getEveStoreObject() throws RemoteException{
//		int count = 0;
//		Object obj = null;
//		try {
//			Properties prop = new Properties();
//			prop.load(new FileInputStream("service-config.properties"));
//			Enumeration counten = prop.elements();
//			int noOfServices = 0;
//			while(counten.hasMoreElements()) {
//				noOfServices = noOfServices + 1;
//				System.out.println(counten.nextElement());
//			}
//			Class c[] = new Class[noOfServices];
//			Object o[] = new Object[noOfServices];
//			Enumeration en = prop.elements();
//			while(en.hasMoreElements()) {
//				String serviceConfigFile = (String) en.nextElement();
//				Properties servicesProp = new Properties();
//				servicesProp.load(new FileInputStream(serviceConfigFile));
//				String interfaceimpl = servicesProp.getProperty("interfaceimpl");
//				String interfacename = servicesProp.getProperty("interfacename");
//				o[count] = Class.forName(interfaceimpl).getConstructor(null).newInstance(null);
//				c[count] = Class.forName(interfacename);
//				++count;
//			}
//			 obj = Proxy.newProxyInstance(new MyApp().getClass().getClassLoader(), c,
//				   new MyInvocationHandler(o));
//		return obj;	
//		}catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
	@Override
	public Object getEveStoreObject() throws RemoteException {
	int count=0;
	try {
		Properties prop=new Properties();
		prop.load(new FileInputStream("service-config.properties"));
		Enumeration counten=prop.elements();
		int noofservices=0;
		while(counten.hasMoreElements()) {
			noofservices=noofservices+1;
			System.out.println(counten.nextElement());
		}
		Class c[]=new Class[noofservices];
		Object o[]=new Object[noofservices];
		Enumeration en=prop.elements();
		while(en.hasMoreElements()) {
			String serviceConfigFile=(String)en.nextElement();
			Properties servicesProp=new Properties();
			servicesProp.load(new FileInputStream(serviceConfigFile));
			String interfaceimpl=servicesProp.getProperty("interfaceimpl");
			String interfacename=servicesProp.getProperty("interfacename");
			o[count]=Class.forName(interfaceimpl).getConstructor(null).newInstance(null);
			c[count]=Class.forName(interfacename);
			//list.add(Class.forName(interfacename));
			//list.add(interfacename);
			++count;
			}
		
		Object obj=Proxy.newProxyInstance(new MyApp().getClass().getClassLoader(), 
				c,new MyInvocationHandler(o));
		//System.out.println("The object...:"+obj);
		return obj;
	}catch(Exception e) {
		e.printStackTrace();
		return null;
	}
	}

	public static void main(String[] args) throws Exception {
		StoresServer server = new StoresServer();
		LocateRegistry.createRegistry(1999);
		Naming.bind("rmi://localhost:1999/evestore", server);
		System.out.println("Server is Ready");
	}
	
}	

class MyInvocationHandler implements InvocationHandler, Serializable { 
	 Object o[]; 
	 public MyInvocationHandler(Object[] o) 
	 { 
		 this.o = o; 
	 }
 
	 @Override 
  	 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{ 
	  Object returnObj = null; 
	  for(Object obj : o) 
	  { 
		  try {
			  returnObj=method.invoke(obj, args);

		  	}catch(Exception e) {} 
	  } 
	  return returnObj; 
	  } 
  }
 

class MyApp implements Serializable{}