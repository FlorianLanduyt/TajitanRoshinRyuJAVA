package exceptions;

public class DatumIntervalException extends IllegalArgumentException {
    
    public DatumIntervalException() {
        this("Datum 'tot' kan niet voor datum 'van' liggen!");
    }
    
    public DatumIntervalException(String message) {
        super(message);
    }
    
}
