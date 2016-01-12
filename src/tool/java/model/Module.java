package model;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by HU on 03.01.2016.
 */
@XmlRootElement(name="Module")
@XmlAccessorType(XmlAccessType.FIELD)
@Builder @NoArgsConstructor @AllArgsConstructor @ToString @Getter
public class Module {
    @XmlAttribute
    private String name;

    @XmlElement(name="Layer")
    @Singular private List<Layer> layers;


}
