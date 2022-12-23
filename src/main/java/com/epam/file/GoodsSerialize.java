package com.epam.file;

import com.epam.container.TransportList;
import com.epam.transport.Automobile;
import com.epam.transport.Transport;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GoodsSerialize<T extends Transport> {
    private String fileName;

    public GoodsSerialize(String fileName) {
        this.fileName = fileName;
    }

    public TransportList<T> read() {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            TransportList<T> transportList = (TransportList<T>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return transportList;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Read exception");
        }
        return null;
    }

    public void save(TransportList<T> transportList) {
        defaultSave(transportList);
    }

    public void save(TransportList<T> transportList, int times) {
        for (int i = 0; i < times; i++) {
            save(transportList);
        }
    }

    private void defaultSave(TransportList<T> transportList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(transportList);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Write exception");
        }
    }

    public void saveGzip(TransportList<T> transportList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName + "(Gzip)");
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream);

            objectOutputStream.writeObject(transportList);

            objectOutputStream.close();
            fileOutputStream.close();
            gzipOutputStream.close();
        } catch (IOException e) {
            System.out.println("Write exception");
        }
    }
}