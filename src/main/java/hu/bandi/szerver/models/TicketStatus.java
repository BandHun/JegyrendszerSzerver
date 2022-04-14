package hu.bandi.szerver.models;

public enum TicketStatus {
    TODO, STARTED_PROGRESS, WAITING_FOR_REVIEW, WAITING_FOR_TEST, DEMO, DONE;

    TicketStatus() {
    }

    public boolean isValidChange(final TicketStatus fromStatus, final TicketStatus toStatus) {
        if (toStatus == TODO) {
            return true;
        }
        if (fromStatus == TODO && toStatus == STARTED_PROGRESS) {
            return true;
        }
        if (fromStatus == STARTED_PROGRESS && (toStatus == WAITING_FOR_REVIEW || toStatus == WAITING_FOR_TEST)) {
            return true;
        }
        if (fromStatus == WAITING_FOR_REVIEW && toStatus == WAITING_FOR_TEST) {
            return true;
        }

        if (fromStatus == WAITING_FOR_TEST && (toStatus == DEMO || toStatus == DONE)) {
            return true;
        }
        return false;
    }
}
