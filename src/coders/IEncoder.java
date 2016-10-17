package coders;

import information.Information;

public interface IEncoder {
	public abstract Information<Float> encode(Information<Boolean> msg);
}
