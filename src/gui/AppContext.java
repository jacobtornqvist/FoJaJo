package gui;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class AppContext {
	private StringProperty statusString;
	private ObjectProperty<Color> statusColor;

	public AppContext() {
		this.statusString = new SimpleStringProperty();
		this.statusColor = new SimpleObjectProperty<Color>();
	}

	public StringProperty getStatusStringProp() {
		return statusString;
	}

	public ObjectProperty<Color> getStatusColorProp() {
		return statusColor;
	}

	public void setStatus(String status) {
		this.statusString.set(status);
		this.statusColor.set(Color.BLACK);
	}

	public void setStatus(String status, Color color) {
		this.statusString.set(status);
		this.statusColor.set(color);
	}
	public void setSuccess(String status) {
		this.statusString.set(status);
		this.statusColor.set(Color.GREEN);
	}
	public void setError(String status) {
		this.statusString.set(status);
		this.statusColor.set(Color.RED);
	}
	public void clear() {
		this.statusString.set("");
	}
}
