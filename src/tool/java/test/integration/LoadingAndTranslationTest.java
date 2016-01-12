package test.integration;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import sandbox.Translate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by HU on 12.01.2016.
 */
public class LoadingAndTranslationTest {
    private model.System model;
    private Translate sut;

    @Before
    public void setup() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(model.System.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File xml = new File("src/tool/resources/test/petclinic/testmodel001.xmi");
        model = (model.System) unmarshaller.unmarshal(xml);
        sut = new Translate();
    }

    @Test
    public void simpleTranslationTest() {

        final String expectedResult = "MATCH\n" +
            "         (type:Package)-[:DEPENDS_ON]->(otherType:Package)\n" +
            "         WHERE type.name = 'web' and not (\n" +
            "         otherType.name = 'model' or otherType.name='web' or otherType.name='service'\n" +
            "         )\n" +
            "         RETURN DISTINCT\n" +
            "         type as InvalidLayer, otherType as Dependency";
        String result = sut.translate(model);
        Assert.assertEquals(expectedResult,result);
    }
}
