package model;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by HU on 03.01.2016.
 */
@XmlRootElement(name="Layer")
@XmlAccessorType(XmlAccessType.FIELD)
@Builder @NoArgsConstructor @AllArgsConstructor @ToString
public class Layer {
    @XmlAttribute
    private String name;

    @XmlElement(name="usage")
    @Singular("usage") private Set<Layer> usageList;
}
