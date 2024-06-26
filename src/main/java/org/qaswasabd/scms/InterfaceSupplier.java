package org.qaswasabd.scms;

import java.io.IOException;

public interface InterfaceSupplier {
    int addSupplier() throws IOException;
    int updateSupplier() throws IOException;
    void deleteSupplier() throws IOException;
}
