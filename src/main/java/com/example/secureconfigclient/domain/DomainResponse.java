package com.example.secureconfigclient.domain;

/**
 * A simple example model, which Jackson should be able to serialize out-of-the-box.
 */
public final class DomainResponse {

    private final String content;

    private final String code;

    private DomainResponse(final String content, final String code) {
        this.content = content;
        this.code = code;
    }

    public static DomainResponse of(final String content, final String code) {
        final DomainResponse dr = new DomainResponse(content, code);

        return dr;
    }

    public String getContent() {
        return content;
    }

    public String getCode() {
        return code;
    }

}
