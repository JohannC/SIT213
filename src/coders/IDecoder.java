package coders;

import information.Information;

public interface IDecoder {
	public abstract Information<Boolean> decode(Information<Float> msg);
}
