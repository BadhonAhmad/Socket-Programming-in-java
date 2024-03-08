package com.example.chat_by_socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;// read bytes stream as character stream
        OutputStreamWriter outputStreamWriter = null;//this two streams are bridges for bytes to character stream
        BufferedReader bufferedReader = null;//buffer reader and writer will increase the efficiency of input and output
        BufferedWriter bufferedWriter = null;
        try{
            socket = new Socket("localhost",1030);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            //character streams end with reader or writer , bytes stream with Stream
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedReader = new BufferedReader(inputStreamReader);

            Scanner scanner = new Scanner(System.in);//input from console or keyboard

            while (true){

                String msgToSend = scanner.nextLine();
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();//the buffer is full so the flush method is called

                System.out.println("Server : "+bufferedReader.readLine());

                if(msgToSend.equalsIgnoreCase("Bye"))
                    break;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                if(socket != null)
                    socket.close();
                if(inputStreamReader != null)
                    inputStreamReader.close();
                if(outputStreamWriter != null)
                    outputStreamWriter.close();
                if(bufferedReader != null)
                    bufferedReader.close();
                if(bufferedWriter != null)
                    bufferedWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
