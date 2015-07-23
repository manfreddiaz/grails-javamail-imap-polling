package grails.javamail.imap.polling



class IMAPPollingJob {
    static triggers = {
      simple repeatInterval: 30000l // execute job once in 30 seconds
    }

    def execute() {
        // execute job
    }
}
