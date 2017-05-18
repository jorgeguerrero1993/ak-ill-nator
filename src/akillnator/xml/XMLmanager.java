package akillnator.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import akillnator.pojo.Patient;

public class XMLmanager {
	
	File XMLFile=new File("./xmls/Patients.xml");
	public void marshalling(Patient pat){
		
		try {
			JAXBContext jaxbc=JAXBContext.newInstance(Patient.class);
			Marshaller marshaller = jaxbc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			marshaller.marshal(pat, XMLFile);
			marshaller.marshal(pat, System.out);
			System.out.println("                   IT IS MARSHALLED             ");
			
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public void unmarshalling(File XMLFile){
		
		try{
			JAXBContext jaxbc=JAXBContext.newInstance(Patient.class);
			Unmarshaller jaxbu=jaxbc.createUnmarshaller();
			Patient pat=(Patient) jaxbu.unmarshal(XMLFile);
			System.out.println(pat);
		}catch(JAXBException ex){
			ex.printStackTrace();
		}
	}
}
