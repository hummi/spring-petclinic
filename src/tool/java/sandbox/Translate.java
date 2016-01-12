package sandbox;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.Layer;
import model.System;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HU on 11.01.2016.
 */
public class Translate {
    public void main(String[] args) {
        /**
         * MATCH
         (type:Package)-[:DEPENDS_ON]->(otherType:Package)
         WHERE type.name = 'web' and not (
         otherType.name = 'model' or otherType.name='web' or otherType.name='service'
         )
         RETURN DISTINCT
         type, otherType
         */
    }

    @Data @AllArgsConstructor
    public class LayerRel {
        Layer source;
        Layer target;
    }

    public String translate(System model) {
        List<Layer> layerRels = model.getModules().stream().flatMap(m->
            m.getLayers().stream()
        ).collect(Collectors.toList());

        return layerRels
            .stream()
            .map(Translate::translateLayerUsage)
            .collect(Collectors.joining("\n\n"));
    }

    private static String translateLayerUsage(Layer layer) {
        StringBuilder constraint = new StringBuilder();

        constraint.append("MATCH\n")
        .append("         (type:Package)-[:DEPENDS_ON]->(otherType:Package)\n")
            .append("         WHERE type.name = '")
            .append(layer.getName())
            .append("'")
            .append(layer.getUsageList().isEmpty()?"":" and not (\n")
            .append("         ")
            .append(
                layer.getUsageList()
                    .stream()
                    .map(t->"otherType.name ='"+t.getName()+"'")
                    .collect(Collectors.joining(" or "))
            )
            .append(layer.getUsageList().isEmpty()?"\n":"         )\n")
            .append("         RETURN DISTINCT\n")
            .append("         type as InvalidLayer, otherType as Dependency");

        return constraint.toString();
    }
}
