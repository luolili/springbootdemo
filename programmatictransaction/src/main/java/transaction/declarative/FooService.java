package transaction.declarative;

public interface FooService {
    void insertRecord();

    void insertThenRollBack() throws RollbackException;

    void invokeInsertThenRollBack() throws RollbackException;


}
