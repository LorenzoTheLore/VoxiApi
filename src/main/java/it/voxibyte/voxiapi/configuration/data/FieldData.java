package it.voxibyte.voxiapi.configuration.data;

import it.voxibyte.voxiapi.annotation.VoxiField;

import java.lang.reflect.Field;

public class FieldData {
    private final String path;
    private final String comment;

    public static FieldData of(VoxiField voxiField, Field field) {
        return new FieldData(voxiField.path(), voxiField.comments(), field);
    }

    private FieldData(String path, String comment, Field field) {
        this.path = path.isEmpty() ? field.getName() : path;
        this.comment = comment.isEmpty() ? null : comment;
    }

    public String getPath() {
        return path;
    }

    public String getComment() {
        return comment;
    }
}
