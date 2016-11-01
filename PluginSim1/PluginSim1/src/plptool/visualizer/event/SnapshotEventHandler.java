package plptool.visualizer.event;

import org.json.JSONException;

public abstract class SnapshotEventHandler implements SnapshotListener {
	@Override
	public void receiveSnapshot(String color) throws JSONException {};
}