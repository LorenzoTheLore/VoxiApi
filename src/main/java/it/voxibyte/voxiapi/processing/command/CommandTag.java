package it.voxibyte.voxiapi.processing.command;

import java.lang.annotation.*;

/**
 * Defines a command for the plugin.
 * <p>
 * See: https://www.spigotmc.org/wiki/plugin-yml/
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CommandsTag.class)
public @interface CommandTag {

    /**
     * The name of this command.
     * <p>
     * Should be identical to the command's label: e.x. '/LABEL (args...)'
     *
     * @return The name of this command
     */
    String name();

    /**
     * The brief description of this command.
     * <p>
     * Should be a single sentence.
     *
     * @return The description of this command
     */
    String desc();

    /**
     * The expected usage of this command.
     * <p>
     * This is shown to the {@link org.bukkit.entity.Player} when the linked
     * {@link org.bukkit.command.CommandExecutor} returns false.
     * <p>
     * Should resemble '/LABEL expectedArg1 expectedArg2 ...'
     *
     * @return The expected usage of this command
     */
    String usage();

    /**
     * The permission string associated with this command.
     * <p>
     * Minecraft checks if the {@link org.bukkit.command.CommandSender} has
     * the correct privileges to use this command by checking if the
     * CommandSender has this permission string in their records.
     * <p>
     * The checked record will vary based on any permissions plugin may or may
     * not be included with the server.
     *
     * @return The permission string associated with this command
     */
    String permission();

    /**
     * Additional command labels a {@link org.bukkit.command.CommandSender} may
     * use in order to execute this command.
     *
     * @return Additional command labels for this command
     */
    String[] aliases() default {};

    /**
     * An alternative message sent to the {@link org.bukkit.command.CommandSender}
     * if they do not have permission to use this command.
     *
     * @return An alternative insufficient permissions message for this command
     */
    String permissionMessage() default "";

}
