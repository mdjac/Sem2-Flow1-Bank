package Exceptions;
// Jons hjemmebryggede exception klasse

import Logging.LogFile;

public class BankException extends Exception {
    public BankException(String message) {
        super(message);
        LogFile.writeToLog(message);
    }
}
