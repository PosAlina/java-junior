package com.acme.edu.saver;

import com.acme.edu.exceptions.SaveFailureException;

public interface Saver {
    void save(String message) throws SaveFailureException;
}
