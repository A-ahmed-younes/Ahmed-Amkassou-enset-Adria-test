package ma.enset.core.exception;

public class DuplicateEntryException extends BusinessException {
    public DuplicateEntryException(String key, Object[] args) {
        super(key, args);
    }
}
