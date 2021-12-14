package com.chat.models;

public class Message {

    //message object for our chat application

    private String sender;
    private String message;
    private String time;

    public Message(){
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString(){
        return  String.format("%s: %s  %s\n",this.sender,this.message,this.time);
    }
}
