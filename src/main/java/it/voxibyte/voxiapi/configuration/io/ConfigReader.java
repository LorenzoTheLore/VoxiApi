package it.voxibyte.voxiapi.configuration.io;

import it.voxibyte.voxiapi.annotation.VoxiField;
import it.voxibyte.voxiapi.configuration.data.FieldData;
import it.voxibyte.voxiapi.util.ReflectionUtil;
import it.voxibyte.voxiapi.util.YamlUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;

public class ConfigReader {
    private final File file;

    public ConfigReader(File file) {
        this.file = file;
    }

    public <T> T read(Class<T> clazz) throws FileNotFoundException, IllegalAccessException {
        Yaml yaml = YamlUtil.getYaml();
        T instance = ReflectionUtil.obtainInstance(clazz);

        Map<String, Object> values = yaml.load(new FileReader(file));
        Map<Field, VoxiField> fields = ReflectionUtil.getAnnotatedFields(clazz, VoxiField.class);

        for (Field field : fields.keySet()) {
            field.setAccessible(true);
            FieldData fieldData = FieldData.of(fields.get(field), field);
            String path = fieldData.getPath();

            field.set(instance, getValueFromNestedMap(values, path));
        }

        return instance;
    }

    private Object getValueFromNestedMap(Map<String, Object> nestedMap, String key) {
        String[] keys = key.split("\\.");
        Object value = nestedMap;

        for (String k : keys) {
            if (value instanceof Map) {
                value = ((Map<String, Object>) value).get(k);
            } else {
                return null;
            }
        }

        return value;
    }
}
