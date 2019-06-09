import java.util.EventObject;

public class AdDisplayerChangeEvent extends EventObject {

    private boolean wasTurnedOn;
    private boolean textWasChanged;
    private boolean timeWasChanged;

    public AdDisplayerChangeEvent(Integer source, boolean wasDisplayedOn, boolean textWasChanged, boolean timeWasChanged) {
        super(source);
        this.wasTurnedOn = wasDisplayedOn;
        this.textWasChanged = textWasChanged;
        this.timeWasChanged = timeWasChanged;
    }

    public Integer getSource() {
        return (Integer) super.getSource();
    }

    public boolean isTextWasChanged(){
        return this.textWasChanged;
    }

    public boolean isWasTurnedOn() {
        return wasTurnedOn;
    }

    public boolean isTimeWasChanged() {
        return timeWasChanged;
    }

}
