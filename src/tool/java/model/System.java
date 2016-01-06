package model;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by HU on 03.01.2016.
 */
@XmlRootElement(name="System")
@XmlAccessorType(XmlAccessType.FIELD)
@Builder @NoArgsConstructor @AllArgsConstructor @ToString
public class System {
    @XmlAttribute
    private String name;

    @XmlElement(name="Module")
    @Singular
    private List<Module> modules;
}

