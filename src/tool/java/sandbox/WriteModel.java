package sandbox;

import model.Layer;
import model.Module;
import model.System;
import model.System.SystemBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by HU on 03.01.2016.
 */
public class WriteModel {

    public static void main(String args[]) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(model.System.class);

//        Unmarshaller unmarshaller = jc.createUnmarshaller();
//        File xml = new File("src/forum15881876/input.xml");
//        System sys = (System) unmarshaller.unmarshal(xml);
        Layer layUI = Layer.builder().name("ui").build();
        Layer layPer = Layer.builder().name("Per").build();
        Layer laySer = Layer.builder().name("Ser").build();
        Module mod = Module.builder().name("catalog").layer(layUI).layer(laySer).layer(layPer).build();
        System sys = System.builder().name("petclinic").module(mod).build();


        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "SummaryCart.xsd");
        marshaller.marshal(sys, java.lang.System.out);
    }
}
