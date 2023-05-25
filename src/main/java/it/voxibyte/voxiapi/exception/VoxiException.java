package it.voxibyte.voxiapi.exception;

/**
 * Generic representation of exception for plugins using VoxiApi
 */
public class VoxiException extends RuntimeException {
    public VoxiException(String message) {
        super(message);
    }
}
