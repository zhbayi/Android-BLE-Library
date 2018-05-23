package no.nordicsemi.android.ble;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.MtuCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

public final class MtuRequest extends Request<MtuCallback> {
	private final int value;

	MtuRequest(final @NonNull Type type, int mtu) {
		super(type);
		if (mtu < 23)
			mtu = 23;
		if (mtu > 517)
			mtu = 517;
		this.value = mtu;
	}

	@Override
	@NonNull
	public MtuRequest done(final @NonNull SuccessCallback callback) {
		this.successCallback = callback;
		return this;
	}

	@Override
	@NonNull
	public MtuRequest fail(final @NonNull FailCallback callback) {
		this.failCallback = callback;
		return this;
	}

	@Override
	@NonNull
	public MtuRequest with(final @NonNull MtuCallback callback) {
		this.valueCallback = callback;
		return this;
	}

	void notifyMtuChanged(final @NonNull BluetoothDevice device, final int mtu) {
		if (valueCallback != null)
			valueCallback.onMtuChanged(device, mtu);
	}

	int getRequiredMtu() {
		return value;
	}
}