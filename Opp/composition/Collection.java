import java.util.Objects;

public interface Collection {
    
    default boolean isEmpty() {
        return getSize() == 0;
    }
    
    default int getSize() {
        return getValues().length;
    }
    
    default boolean contains(String value) {
        for (String datum : getValues()) {
            if (Objects.equals(datum, value)) {
                return true;
            }
        }
        return false;
    }
    
    String[] getValues();
}