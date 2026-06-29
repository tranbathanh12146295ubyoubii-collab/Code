
package ReaderModel;

import ObserverUI.ObserverReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReaderService {
    private ReaderRepository readerRepository;
    private List<ObserverReader> observer = new ArrayList<>();

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }
    

    public ReaderRepository getReaderRepository() {
        return readerRepository;
    }
    public void setReaderRepository(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    // Observer
    public void registerObserver(ObserverReader o) {
        observer.add(o);
    }

    public void removeObserver(ObserverReader o) {
        observer.remove(o);
    }

    public void notifyUpdateReader(List<Reader> readers) {
        for (ObserverReader observerReader : observer) {
            observerReader.updateDisplay(readers);
        }
    }
    
   

    public boolean addReader(Reader reader) {
    	if (reader == null) return false;
        if (this.getReaderRepository().addReader(reader)) {
            notifyUpdateReader(this.getReaderRepository().getReaders());
            return true;
        }  return false;
    }

    public boolean removeReader(Reader reader) {
        if (this.getReaderRepository().removeReader(reader)) {
            notifyUpdateReader(this.getReaderRepository().getReaders());
            return true;
        } return false;
    }

    public Reader createReader(String id, String name, String sex, LocalDate birth, String address, String cccd, String numberphone) {
        if (!notContainReader(id, cccd, numberphone)) return null;
        return new Reader(id, name, sex, birth, address, cccd, numberphone);
    }

    // serch
    public List<Reader> findByName(String name) {
        List<Reader> reader = new ArrayList<>();
        if (readerRepository.getReaders() == null || readerRepository.getReaders().isEmpty()) {
        	notifyUpdateReader(reader);
            return reader;
        }
        String f = name.toLowerCase().trim();
        for (Reader r : this.readerRepository.getReaders()) {
			if (r.getName().toLowerCase().contains(f)) {
				reader.add(r);
			}
		}
        notifyUpdateReader(reader);
        return reader;
    }
    
    public Reader findByID(String id) {
        return readerRepository.getReaders().stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    public Reader findByNumberPhone(String numberPhone) {
        return readerRepository.getReaders().stream().filter(r -> r.getNumberPhone().equals(numberPhone)).findFirst().orElse(null);
    }

    public Reader findByCCCD(String cccd) {
       return readerRepository.getReaders().stream().filter(r -> r.getCccd().equals(cccd)).findFirst().orElse(null);
    }

    // check contain 
    public boolean notContainReader(String id, String cccd, String numberphone) {
        return findByID(id) == null && findByCCCD(cccd) == null && findByNumberPhone(numberphone) == null;
    }
    /**
     * 
     */

    public boolean editReader(String id, String name, String sex, LocalDate birth, String cccd, String address, String numberphone) {
    	if (this.findByID(id) == null) return false;
    	for (Reader r : this.getReaderRepository().getReaders()) {
			if (r.getId().equals(id)) {
				r.setName(name);
				r.setSex(sex);
				r.setAddress(address);
				r.setCccd(cccd);
				r.setBirth(birth);
				r.setNumberPhone(numberphone);
				break;
			}
		}
    	notifyUpdateReader(this.getReaderRepository().getReaders());
    	return true;
    }
    
    public List<Reader> findListReaderById(List<String> id) {
    	List<Reader> list = new ArrayList<>();
    	for (String i : id) {
    		Reader r = findByNumberPhone(i);
    		if (r != null) {
    			list.add(r);
    		}
    	}
    	notifyUpdateReader(list);
    	return list;
    }
    
    public List<Reader> getAllReader(){
    	return this.readerRepository.getReaders();
    }
     



    

}
