package com.acme.edu.saver;

import com.acme.edu.exceptions.SaveException;

public interface Saver {
    void save(String message) throws SaveException;
}
