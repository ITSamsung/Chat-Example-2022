package com.samsung.chatexample.models.domain;

import java.util.Objects;

public class DialogMetadataD {
    public long createDate;

    public DialogMetadataD() {
    }

    public DialogMetadataD(long createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DialogMetadataD that = (DialogMetadataD) o;
        return createDate == that.createDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(createDate);
    }

    @Override
    public String toString() {
        return "DialogMetadata{" +
                "createDate=" + createDate +
                '}';
    }
}
