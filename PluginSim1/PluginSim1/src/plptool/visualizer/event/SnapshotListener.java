package plptool.visualizer.event;

import org.json.JSONException;

public interface SnapshotListener {
	void receiveSnapshot(String color) throws JSONException;
}