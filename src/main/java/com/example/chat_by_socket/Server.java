package com.example.chat_by_socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;// read bytes stream as character stream
        OutputStreamWriter outputStreamWriter = null;//this two streams are bridges for bytes to character stream
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;//buffer reader and writer will increase the efficiency of input and outpu
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(1030);

        while (true){//this for to connect a client
            try{
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while(true){//this for chatting with the client
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("Client :" + msgFromClient);
                    bufferedWriter.write("msg received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    if(msgFromClient.equalsIgnoreCase("bye"))
                        break;
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedWriter.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
