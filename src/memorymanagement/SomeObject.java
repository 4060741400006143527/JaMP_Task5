package memorymanagement;

public class SomeObject {

    private SomeObject someObject;
    private final long[] intArray = new long[1000];

    public SomeObject() {
    }

    public SomeObject(SomeObject someObject) {
        this.someObject = someObject;
    }

}
