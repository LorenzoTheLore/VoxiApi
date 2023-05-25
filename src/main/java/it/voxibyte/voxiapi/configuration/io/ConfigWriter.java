package it.voxibyte.voxiapi.configuration.io;

import com.google.common.io.Files;
import it.voxibyte.voxiapi.annotation.VoxiField;
import it.voxibyte.voxiapi.configuration.data.FieldData;
import it.voxibyte.voxiapi.util.MapUtil;
import it.voxibyte.voxiapi.util.ReflectionUtil;
import it.voxibyte.voxiapi.util.YamlUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ConfigWriter {
    private final File file;

    public ConfigWriter(File file) {
        this.file = file;
    }

    public <T> void write(T instance) throws IllegalAccessException, IOException {
        Class<?> configurationClass = instance.getClass();
        Yaml yaml = YamlUtil.getYaml();

        Map<Field, VoxiField> fields = ReflectionUtil.getAnnotatedFields(configurationClass, VoxiField.class);
        Map<String, Object> serialized = new HashMap<>();

        for(Field field : fields.keySet()) {
            field.setAccessible(true);
            FieldData fieldData = FieldData.of(fields.get(field), field);

            serialized.put(fieldData.getPath(), field.get(instance));
        }

        BufferedWriter fileWriter = Files.newWriter(file, Charset.defaultCharset());
        yaml.dump(MapUtil.toNestedMap(serialized), fileWriter);
        fileWriter.close();
    }
}
