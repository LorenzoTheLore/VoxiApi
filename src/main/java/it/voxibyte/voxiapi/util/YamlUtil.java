package it.voxibyte.voxiapi.util;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

public class YamlUtil {
    private static Yaml yaml;

    public static Yaml getYaml() {
        if(yaml == null) {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);


            yaml = new Yaml(options);
            yaml.setBeanAccess(BeanAccess.FIELD);
        }

        return yaml;
    }
}
