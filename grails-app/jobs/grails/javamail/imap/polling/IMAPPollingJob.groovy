package grails.javamail.imap.polling

import Helpers.EmailHelper
import Models.ReceivedMessageModel


class IMAPPollingJob {
    static triggers = {
      simple repeatInterval: 30000l // execute job once in 30 seconds
    }

    def execute() {

        // execute job
        String emailPersonalName = "GoMentr Omar";
        String emailAddress = "oaddam@gomentr.com";
        String emailPassword = "mathyboy99";
        String emailReplyTo = "oaddam@gomentr.com";

        String senderHost = "smtp.gmail.com";
        String senderPort = "587";

        String receivingHost = "imap.gmail.com";
        String receivingPort = null;

        String inboxFolderName = "inbox";
        String processedEmailsFolderName =  "Processed";
        String errorEmailsFolderName = "UnProcessed";

        //Email Full Package
        EmailHelper emailFullPackage = new EmailHelper(emailPersonalName, emailAddress, emailPassword, emailReplyTo, senderHost, senderPort, receivingHost, receivingPort, inboxFolderName, processedEmailsFolderName, errorEmailsFolderName);
        emailFullPackage.SendEmail("ID-11", "omaddam@gmail.com", "Testing my sender plugin", "I told you that i am just testing it!!!!");
        List<ReceivedMessageModel> emails = emailFullPackage.readEmails(true);
        System.out.println("Total: " + emails.size());
        for(ReceivedMessageModel message : emails) {
            System.out.println("****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");
            System.out.println("ID: " + message.getID().toString());
            System.out.println("From: " + message.getFromAddress());
            System.out.println("Subject: " + message.getSubject());
            System.out.println("Parsed Content:" + message.getParsedContent());// + "\n** Unparsed:" + message.getContent());
        }
    }
}
