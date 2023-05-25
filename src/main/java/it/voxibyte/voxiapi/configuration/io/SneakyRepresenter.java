package it.voxibyte.voxiapi.configuration.io;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.Set;

public class SneakyRepresenter extends Representer {

    public SneakyRepresenter(DumperOptions options) {
        super(options);
    }

    @Override
    protected MappingNode representJavaBean(Set<Property> properties, Object javaBean) {
        if (!classTags.containsKey(javaBean.getClass()))
            addClassTag(javaBean.getClass(), Tag.MAP);

        return super.representJavaBean(properties, javaBean);
    }

}
