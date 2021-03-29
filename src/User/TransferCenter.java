package User;

//import CommonClasses.AbstractDataBlock;
//import CommonClasses.DataBlock;

import CommonClasses.FirstTimeConnectedData;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class TransferCenter{

    DatagramSocket datagramSocket;
    DatagramSocket datagramSocketForWork;

    InetSocketAddress mainServerSocketAddress;
    InetSocketAddress socketAddressReceive;
    InetSocketAddress individualServerSocketAddress;

    private final int SIZEOFBUFFER = 500;

    public TransferCenter(){
        boolean isExceptions = true;
        while (isExceptions) {
            isExceptions = false;
            try {
                createMainServerSocketAddress();
                createSocketAddressForReceive();
//                RestartConnectionWithServer restartConnectionWithServer = new RestartConnectionWithServer();
                CreateConnectionWithServer createConnectionWithServer = new CreateConnectionWithServer(this);
                createConnectionWithServer.start();
//                Date date = new Date();
                long timeOfStart = new Date().getTime();

//              Жуткий колхоз! ==============================
                while (!createConnectionWithServer.isAllRight() & !(timeOfStart < (new Date().getTime() - 5000))){
                    Thread.sleep(10);

                }
                if(createConnectionWithServer.isAllRight()){
                    System.out.println("Пользователь успешно создан!");
                }
                else {
                    System.out.println("Проблема с подключением к серверу. Пробуем всё заново!");
                    isExceptions = true;
                }
//              ==============================================


            } catch (Exception e) {
                System.out.println("Введены некорректные данные!");
                isExceptions = true;
            }
        }


    }


    public TransferCenter(TransferCenter transferCenter){
        this.mainServerSocketAddress = transferCenter.mainServerSocketAddress;
        createSocketAddressForReceive();
        createConnectionWithServer();
    }

    public void createConnectionWithServer(){
        FirstTimeConnectedData firstTimeConnectedData = new FirstTimeConnectedData();
//        DatagramChannel datagramChannel = DatagramChannel.open();
//        System.out.println("1");
        firstTimeConnectedData.setSocketAddress(socketAddressReceive);
//        System.out.println("2");
//        .parameter = socketAddressReceive.getAddress().getHostAddress() + "; " + socketAddressReceive.getPort();
        sendObjectToServer(firstTimeConnectedData);
//        System.out.println("3");
        firstTimeConnectedData = (FirstTimeConnectedData) receiveObjectFromServer();
//        System.out.println("4");
//        System.out.println(firstTimeConnectedData.getSocketAddress());

//        individualServerSocketAddress = new InetSocketAddress(firstTimeConnectedData.getSocketAddress());

        individualServerSocketAddress = (InetSocketAddress) firstTimeConnectedData.getSocketAddress();
//        System.out.println("5");
    }

//    public Object receiveObjectFromServer(){
//        return receiveObjectFromServer(receiveObjectArrSize());
//    }

    public Object receiveObjectFromServer(){
//        byte[] buffer = new byte[10000];
//        try {
//            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
//            datagramSocket.receive(datagramPacket);
//        } catch (IOException e) {
//            System.out.println("Проблема с переходом из канала в буфер!");
//            e.printStackTrace();
//        }
//        return deSerialize(buffer);

        Object obj = null;
        boolean endOfReceive = false;
        byte[] objByteArr = new byte[0];
        byte[] receivedArr;

        while (!endOfReceive){
            receivedArr = receiveByteArr(datagramSocket);
            byte[] newArr = new byte[objByteArr.length + receivedArr.length];

            for(int i =0;i<(objByteArr.length + receivedArr.length);i++){
                if(i<objByteArr.length){
                    newArr[i] = objByteArr[i];
                }
                else {
                    newArr[i] = receivedArr[i-objByteArr.length];
                }
            }
            objByteArr = newArr;


            try {
                obj = ObjectProcessing.deSerializeObject(objByteArr);
                endOfReceive = true;

            }catch (Exception e){}
        }

        return  obj;
    }

    private byte[] receiveByteArr(DatagramSocket datagramSocket) {
        final int size = SIZEOFBUFFER;
//        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[size]);
        byte[] bytes = new byte[size];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        try {
            datagramSocket.receive(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }


//    private Object deSerialize(byte[] objectByteArr){
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(objectByteArr);
//
//        ObjectInputStream objectInputStream = null;
//
//        try {
//            objectInputStream = new ObjectInputStream(inputStream);
//        } catch (IOException e) {
//            System.out.println("Проблема с созданием ObjectInputStream!");
//            e.printStackTrace();
//        }
//
//        Object object = null;
//        try {
//            object = objectInputStream.readObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return object;
//    }

//    public boolean checkConnection(){
//        CheckConnection checkConnection = new CheckConnection(this);
//        checkConnection.start();
//        long time = new Date().getTime() + 1000;
////        System.out.println();
//        while((new Date().getTime() < time) & !checkConnection.connectionWorking){
////            System.out.println(new DataBlock());
//        }
//        if(!checkConnection.connectionWorking){
//            System.out.println("Нет ответа от сервера...");
//            checkConnection();
//        }
//        return false;
//    }

//    /**устанавливает связь с сервером (заполняет поля datagramSocket и socketAddress)*/
    public void createMainServerSocketAddress(){
        Scanner scanner = new Scanner(System.in);
        Boolean err = false;

        System.out.println("Введите ip адрес сервера: ");
        String ip = scanner.nextLine();
        System.out.println("Введите port: ");
        int port = scanner.nextInt();


        //"192.168.1.135"
        mainServerSocketAddress = null;
        //            System.out.println(InetAddress.getLocalHost().getHostAddress());
        mainServerSocketAddress = new InetSocketAddress(ip, port);
//        System.out.println("ttt");
//        socketAddressForSend
//        System.out.println(socketAddressForSend.getPort());
//        socketAddressForSend.isUnresolved()
//        System.out.println(socketAddressForSend.isUnresolved());

//        datagramSocket = null;
//        try {
//            datagramSocket = new DatagramSocket();
////            datagramSocket.bind();
//        } catch (SocketException e) {
////            err = true;
//            System.out.println("Проблемы с datagramSocket!");
//        }


        if(err == true){
            System.out.println("Попробуем снова...");
            createMainServerSocketAddress();
        }

    }
    private int createSocketAddressForReceive(){
        Random random = new Random();

        int port = -1;

        boolean workingSocket = false;
        while (!workingSocket) {
            port = random.nextInt(65535);

            try {
                socketAddressReceive = new InetSocketAddress(InetAddress.getLocalHost(), port);
                workingSocket = true;
            } catch (IOException e) {
                System.out.println("Проблема с созданием порта!");
//                e.printStackTrace();
            }

        }
        try {
            datagramSocket = null;
            datagramSocket = new DatagramSocket(socketAddressReceive);
//            datagramChannelForReceive = DatagramChannel.open();
//            datagramSocket.bind(socketAddressReceive);
//            DatagramPacket datagramPacket = new DatagramPacket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return port;
    }

    public <T> void sendObjectToServer(T object){

//        checkConnection();



        byte[] serObject = ObjectProcessing.serializeObject(object);
//        DataBlock warningAboutSize = new DataBlock();
//        warningAboutSize.parameter = String.valueOf(serObject.length);
//        sendByteArr(serializeObject(warningAboutSize));
//        System.out.println(object.getClass().getName());
        if(object.getClass().getName().equals("CommonClasses.FirstTimeConnectedData")){
            sendByteArr(serObject, mainServerSocketAddress);
        }
        else {
//            System.out.println(serObject.length);
//            Object obj = deSerialize(serObject);
//            System.out.println(obj.getClass().getName());
//            for(int i =0; i< serObject.length;i++){
//                System.out.println(i + "   " + serObject[i]);
//            }
            sendByteArr(serObject, individualServerSocketAddress);
        }
        //Необходимо получать allRight --- доделать
//        sendByteArr(serializeObject(object));
    }


    private void sendByteArr(byte[] bArr, SocketAddress socketAddress){
//        System.out.println(bArr.length);
//        if(bArr.length>500){
//            System.out.println(bArr[602]);
//        }
        final int size = SIZEOFBUFFER;
        byte[] bigArr = new byte[bArr.length + size - (bArr.length % size)];
        for (int i =0; i < bArr.length; i++){
            bigArr[i] = bArr[i];
        }
        bArr = bigArr;


        for(int i = 0; i < Math.ceil(Float.valueOf(bArr.length)/size); i++){
            byte[] data = new byte[size];
            for (int j = 0; j<(size);j++){
                data[j] = bArr[j+(size)*i];
            }

            DatagramPacket datagramPacket = new DatagramPacket(data, size, socketAddress);
            try {
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                System.out.println("Проблема с отправкой объекта!");
            }
        }
    }

//    public <T> byte[] serializeObject(T obj){
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = null;
//        try {
//            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        } catch (IOException e) {
//            System.out.println("Проблема с созданием потока для серилизации объектов!");
//        }
//        byte[] serObj = null;
//
//        try {
//            objectOutputStream.writeObject(obj);
//            serObj = byteArrayOutputStream.toByteArray();
//        } catch (IOException e) {
//            System.out.println("Проблема с серелизацией объекта для отправки на сервер!");
//            e.printStackTrace();
//        }
//
//        return serObj;
//    }




//    public void sendRequestToServer(Object commandsDataForSendToServer){
////        File dataToServ = new File("dataToServ");
//        byte[] serObj = new byte[1000];
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = null;
//        try {
//            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        } catch (IOException e) {
//            System.out.println("Проблема с созданием потока для серилизации объектов!");
//        }
//        try {
//            objectOutputStream.writeObject(commandsDataForSendToServer);
//            serObj = byteArrayOutputStream.toByteArray();
//        }catch (IOException e){
//            System.out.println("Проблема с серелизацией объекта для отправки на сервер!");
//        }
//        DatagramPacket datagramPacket = new DatagramPacket(serObj, serObj.length, socketAddress);
//        try {
//            datagramSocket.send(datagramPacket);
//        } catch (IOException e) {
//            System.out.println("Проблема с отправкой объекта!");
//        }
//
////        System.out.println(serObj.length);
////        for(byte b : serObj){
////            System.out.println(b);
////        }
////        System.out.println(serObj.length);
////        for(byte b : serObj){
////            System.out.println(b);
////        }
////        byte[] b = {1};
////        DatagramPacket datagramPacket = null;
////        datagramPacket = new DatagramPacket(b, b.length, socketAddress);
////        try {
////            datagramSocket.send(datagramPacket);
////        } catch (IOException e) {
////            System.out.println("Проблемы с отправкой запроса на сервер!");
////        }
//    }
//
//    public DataBlock reciveAnswerFromServer(){
//
//        byte[] bArr = new byte[1000];
//        DatagramChannel channel = null;
//
//        try {
//            channel = DatagramChannel.open();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        SocketAddress socketAddress = new InetSocketAddress(6667);
//        try {
//            channel.bind(socketAddress);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ByteBuffer byteBuffer = ByteBuffer.wrap(bArr);
//            byteBuffer.clear();
//        try {
//            socketAddress = channel.receive(byteBuffer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("take == true");
//
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(bArr);
//        ObjectInputStream objectInputStream = null;
//        DataBlock dataBlock = null;
//        try {
//            objectInputStream = new ObjectInputStream(inputStream);
//            dataBlock = (DataBlock) objectInputStream.readObject();
//        } catch (Exception e) {
//            System.out.println("Проблема с загрузкой потока ObjectInputStream!");
//        }
////        System.out.println("ttttttt");
//        return dataBlock;
//    }
//
//    public void sendAnswerToServer(DataBlock dataBlock){
//        sendRequestToServer(dataBlock);
//    }
}