package test.component;

import junit.framework.Assert;
import model.Layer;
import model.Module;
import model.System;
import org.junit.Before;
import org.junit.Test;
import sandbox.Translate;

/**
 * Created by HU on 11.01.2016.
 */
public class TranslateTest {

    private model.System model;
    private Translate sut;

    @Before
    public void setup() {
        Layer layModel = Layer.builder().name("model").build();
        Layer layPer = Layer.builder().name("repository").build();
        Layer laySer = Layer.builder().name("service").usage(layPer).build();
        Layer layUI = Layer.builder().name("web").usage(laySer).usage(layModel).build();
        Module mod = Module.builder().name("catalog").layer(layUI).layer(laySer).layer(layPer).build();
        model = System.builder().name("petclinic").module(mod).build();
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
