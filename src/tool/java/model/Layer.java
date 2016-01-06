package model;

import lombok.*;

import javax.xml.bind.annotation.*;

/**
 * Created by HU on 03.01.2016.
 */
@XmlRootElement(name="Layer")
@XmlAccessorType(XmlAccessType.FIELD)
@Builder @NoArgsConstructor @AllArgsConstructor @ToString
public class Layer {
    @XmlAttribute
    private String name;
}
