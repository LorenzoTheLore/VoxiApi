package it.voxibyte.voxiapi.exception;

/**
 * Exception thrown by VoxiPlugins when something failed to initialize
 */
public class InitializationException extends VoxiException {
    public InitializationException(String message) {
        super(message);
    }

}
