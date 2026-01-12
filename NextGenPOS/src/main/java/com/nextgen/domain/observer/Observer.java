package com.nextgen.domain.observer;

import com.nextgen.domain.Sale;

public interface Observer {
    // Update method: Called when the state of the observed person changes
    void update(Sale sale);
}