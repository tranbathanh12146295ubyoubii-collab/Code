
package ReaderModel;

import java.util.ArrayList;
import java.util.List;

public class ReaderRepository {
    public static ReaderRepository instance;
    private List<Reader> readers;

    private ReaderRepository() {
        readers = new ArrayList<>();
    }

    public static ReaderRepository getInstance() {
        if (instance == null) {
            instance = new ReaderRepository();
        } return instance;
    }
    /**
     * @return List<Reader> return the readers
     */
    public List<Reader> getReaders() {
        return readers;
    }
    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public boolean addReader(Reader reader) {
        if (readers.contains(reader)) {
            return false;
        } 
        readers.add(reader);
        return true;
    }

    public boolean removeReader(Reader reader) {
        if (!readers.contains(reader)) {
            return false;
        } 
        readers.remove(reader);
        return true;
    }
}
