package grails.javamail.imap.polling

import Helpers.EmailHelper
import Models.ReceivedMessageModel


class IMAPPollingJob {
    static triggers = {
      simple repeatInterval: 30000l // execute job once in 30 seconds
    }

    def execute() {
        // execute job
        String emailGateID = "oaddam";
        String emailGateHost = "@gomentr.com";
        String emailPassword = "";

        String senderHost = "smtp.gmail.com";
        String senderPort = "587";

        String receivingHost = "imap.gmail.com";
        String receivingPort = null;

        String inboxFolderName = "inbox";
        String processedEmailsFolderName = "Processed";
        String errorEmailsFolderName = "UnProcessed";

        //Email Sender Only
        EmailHelper emailSenderOnly = new EmailHelper(emailGateID, emailGateHost, emailPassword, senderHost, senderPort);
        //emailSenderOnly.SendEmail("ID", "omaddam@gmail.com", "Testing my sender plugin", "I told you that i am just testing it!!!!");

        //Email Receiver Only
        EmailHelper emailReceiverOnly = new EmailHelper(emailGateID, emailGateHost, emailPassword, receivingHost, receivingPort, inboxFolderName);
        List<ReceivedMessageModel> emails = emailReceiverOnly.readEmails(false);
        System.out.println("Total: " + emails.size());
        for(ReceivedMessageModel message : emails) {
            System.out.println("****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");
            System.out.println(message.getID() + ": " + message.getFromAddress().toString() + " / " + message.getSubject() + "\n** Parsed:" + message.getParsedContent());// + "\n** Unparsed:" + message.getContent());
        }

        //Email Full Package (sender and receiver)
        EmailHelper emailFullPackage = new EmailHelper(emailGateID, emailGateHost, emailPassword, senderHost, senderPort, receivingHost, receivingPort, inboxFolderName, processedEmailsFolderName, errorEmailsFolderName);
        /*List<ReceivedMessageModel> emails = emailFullPackage.readEmails(true);
        System.out.println("Total: " + emails.size());
        for(ReceivedMessageModel message : emails) {
            System.out.println(message.getID() + ": " + message.getFromAddress().toString() + " / " + message.getSubject() + "\n" + message.getParsedContent());
        }*/
    }
}
