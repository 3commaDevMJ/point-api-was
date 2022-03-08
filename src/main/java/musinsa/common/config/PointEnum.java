package musinsa.common.config;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PointEnum {

    INCREASE("increase","적립"),
    DECREASE("decrease","사용"),
    EXPIRE("expire","사용");

    String type;
    String text;

    PointEnum(String type,String text) {
        this.type = type;
        this.text = text;
    }

    public String getValue() {
        return this.type;
    }
    public String getText() {
        return this.text;
    }

}
