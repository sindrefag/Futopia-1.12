package net.thegaminghuskymc.futopia.blocks;

/**
 * Interface which can be put on just about anything to allow for modular registration.
 *
 * @author King Lemming
 */
public interface IInitializer {

	boolean initialize();

	boolean register();

}