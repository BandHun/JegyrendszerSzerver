package hu.bandi.szerver.models;

public enum TicketStatus {
    TODO, IN_PROGRESS, IN_REVIEW, IN_TEST, DEMO, DONE;

    TicketStatus() {
    }

    public static boolean isValidChange(final TicketStatus fromStatus, final TicketStatus toStatus) {
        if (toStatus == TODO) {
            return true;
        }
        if (fromStatus == TODO && toStatus == IN_PROGRESS) {
            return true;
        }
        if (fromStatus == IN_PROGRESS && (toStatus == IN_REVIEW || toStatus == IN_TEST)) {
            return true;
        }
        if (fromStatus == IN_REVIEW && toStatus == IN_TEST) {
            return true;
        }

        if (fromStatus == IN_TEST && (toStatus == DEMO || toStatus == DONE)) {
            return true;
        }
        return false;
    }

    public static TicketStatus parse(String status){
        if(status.equals("TODO")){
            return TODO;
        }
        if(status.equals("IN_PROGRESS")){
            return IN_PROGRESS;
        }
        if(status.equals("IN_REVIEW")){
            return IN_REVIEW;
        }
        if(status.equals("IN_TEST")){
            return IN_TEST;
        }
        if(status.equals("DEMO")){
            return DEMO;
        }
        if(status.equals("DONE")){
            return DONE;
        }
        else {
            throw new RuntimeException("INVALID STATSU");
        }
    }
}
