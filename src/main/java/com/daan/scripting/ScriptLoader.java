package com.daan.scripting;

import java.io.*;
import java.util.function.Supplier;

public interface ScriptLoader {

    default ScriptReader file(File file) {
        return file(() -> file);
    }

    default ScriptReader file(Supplier<File> fileSupplier) {
        return reader(() -> {
            try {
                return new FileReader(fileSupplier.get());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    default ScriptReader stream(InputStream stream) {
        return stream(() -> stream);
    }

    default ScriptReader stream(Supplier<InputStream> streamSupplier) {
        return reader(() -> new InputStreamReader(streamSupplier.get()));
    }

    default ScriptReader reader(Reader reader) {
        return reader(() -> reader);
    }

    ScriptReader reader(Supplier<Reader> readerSupplier);

}
