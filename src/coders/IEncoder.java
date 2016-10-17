package coders;

import information.Information;

/**
 * Interface IEncoder : Permet de Coder un signal
 *
 */
public interface IEncoder {
	public abstract Information<Float> encode(Information<Boolean> msg);
}
