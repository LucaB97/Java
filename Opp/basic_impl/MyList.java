import java.util.Arrays;

public class MyList implements List {

    private String[] data = new String[0];

    public String[] getValues() {
        return Arrays.copyOf(data, data.length);
    }

    public void add(String value) {
        String[] newData = new String[data.length+1];
        System.arraycopy(data, 0, newData, 0, data.length);
        newData[newData.length-1] = value;
        this.data = newData;
    }

    public void insert(int index, String value) {
        String[] newData = new String[data.length+1];
        System.arraycopy(data, 0, newData, 0, index);
        newData[index] = value;
        System.arraycopy(data, index, newData, index+1, data.length-index);
        this.data = newData;
    }

    public void remove(int index) {
        String[] newData = new String[data.length-1];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index+1, newData, index, data.length-index-1);
        this.data = newData;
    }
}