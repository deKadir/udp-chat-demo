package com.chat;
import com.chat.config.ServerConfig;
import com.chat.models.Message;
import com.chat.utils.StringBuilder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException
    {
        //required declarations
        SimpleDateFormat formatter= new SimpleDateFormat(" 'at' HH:mm:ss");
        DatagramSocket serverSocket = new DatagramSocket(ServerConfig.PORT);
        System.out.println("Enter your username:");
        Scanner usernameScanner=new Scanner(System.in);
        String username=usernameScanner.nextLine();
        System.out.format("Welcome %s!\nYou can start typing now!\n",username);

        //message object which we created
        Message message=new Message();
        message.setSender(username);

        //socket and byte arrays 
        Scanner messageScanner =new Scanner(System.in);
        byte[] receiveBuffer = new byte[65535];
        byte[] sendBuffer;
        DatagramPacket packetReceive,packetSend;
        while (true)
        {
            //receive data
            packetReceive = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            serverSocket.receive(packetReceive);
            InetAddress ip= packetReceive.getAddress();
            int port =packetReceive.getPort();
            String receivedData= StringBuilder.data(packetReceive.getData());
            System.out.println(receivedData);

            //send data
            String serverData=messageScanner.nextLine();
                message.setMessage(serverData);
                Date date = new Date(System.currentTimeMillis());
                message.setTime(formatter.format(date));
                sendBuffer=message.toString().getBytes("UTF-8");
                packetSend=new DatagramPacket(sendBuffer,sendBuffer.length,ip,port);
                serverSocket.send(packetSend);

            if(message.getMessage().equalsIgnoreCase("bye")){
                break;
            }
        }
        serverSocket.close();
    }







}
