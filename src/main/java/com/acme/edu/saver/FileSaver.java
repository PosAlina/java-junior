package com.acme.edu.saver;

import com.acme.edu.exceptions.SaveException;

import java.io.*;

public class FileSaver implements Saver {
    private File file;
    private BufferedWriter output;

    public FileSaver(String fileName) throws SaveException {
        try {
            file = new File(fileName);
            file.createNewFile();
            output = new BufferedWriter(
                         new OutputStreamWriter(
                            new BufferedOutputStream(
                                    new FileOutputStream(file))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new SaveException("Couldn't find file '" + fileName + "'");
        } catch (IOException e) {
            e.printStackTrace();
            throw new SaveException("Couldn't create file '" + fileName + "'");
        }
    }

    @Override
    public void save(String message) throws SaveException {
        try {
            output.write(message);
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "Couldn't write message '" + message + "' to the file";
            closeFile(errorMessage);
            throw new SaveException(errorMessage);
        }
    }

    private void closeFile(String errorMessage) throws SaveException {
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SaveException("Couldn't close file when handling exception:" + errorMessage);
        }
    }

    public void closeFile() throws SaveException {
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SaveException("Couldn't close file");
        }
    }

    public File getFile() {
        return file;
    }
}
