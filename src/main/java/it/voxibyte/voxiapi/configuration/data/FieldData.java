package it.voxibyte.voxiapi.configuration.data;

import it.voxibyte.voxiapi.annotation.VoxiField;

import java.lang.reflect.Field;

public class FieldData {
    private final String path;

    public static FieldData of(VoxiField voxiField, Field field) {
        return new FieldData(voxiField.path(), field);
    }

    private FieldData(String path, Field field) {
        this.path = path.isEmpty() ? field.getName() : path;
    }

    public String getPath() {
        return path;
    }
}
