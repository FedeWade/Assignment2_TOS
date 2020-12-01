////////////////////////////////////////////////////////////////////
// Federico Ballarin 1123718
////////////////////////////////////////////////////////////////////


package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable {
    private String mex;

    public TakeAwayBillException(String exc){
        this.mex = exc;
    }

    public String getMessage(){
        return this.mex;
    }
}