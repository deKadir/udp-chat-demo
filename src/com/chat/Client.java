package com.chat;

import com.chat.config.ServerConfig;
import com.chat.models.Message;
import com.chat.utils.StringBuilder;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws IOException
    {
        //formatter for Date() object
        SimpleDateFormat formatter= new SimpleDateFormat("'at' HH:mm:ss");
        //ask username from the user
        Scanner usernameScanner=new Scanner(System.in);
        System.out.println("Enter your username:");
        String username=usernameScanner.nextLine();
        System.out.format("Welcome %s!\nYou can start typing now!\n",username);
        Scanner messageScanner = new Scanner(System.in);


        //message object
        Message message=new Message();
        message.setSender(username);

        //initialize socket we dont need to pass PORT as parameter because here is client side
        DatagramSocket clientSocket = new DatagramSocket();
        // get ip address of user
        InetAddress ip = InetAddress.getLocalHost();
        //buffers for byte array
        byte[] sendDataBuffer = null;
        byte[] receiveDataBuffer=new byte[1024];
        DatagramPacket packetSend,packetReceive;
        while (true)
        {
            //getting message as String from user and setting it to message object
            String clientData = messageScanner.nextLine();
                message.setMessage(clientData);
                Date date = new Date(System.currentTimeMillis());
                message.setTime(formatter.format(date));
                //we convert message object to string then byte array
                sendDataBuffer = message.toString().getBytes("UTF-8");
                //datagram packet takes data,length,ip and port parameters
                packetSend =new DatagramPacket(sendDataBuffer, sendDataBuffer.length, ip, ServerConfig.PORT);
                //sending datagrampacket using socket
                clientSocket.send(packetSend);

            // terminate program
            if (message.getMessage().equals("bye")){
                System.out.println("Disconnected.");
                break;
            }
            //receive message from server
            packetReceive=new DatagramPacket(receiveDataBuffer,receiveDataBuffer.length);
            clientSocket.receive(packetReceive);
            System.out.println(StringBuilder.data(packetReceive.getData()));


        }
        clientSocket.close();
    }

}
