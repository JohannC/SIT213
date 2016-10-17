package coders;

import information.Information;

/**
 * Interface IDecoder : Permet de décoder un signal
 *
 */
public interface IDecoder {
	public abstract Information<Boolean> decode(Information<Float> msg);
}
