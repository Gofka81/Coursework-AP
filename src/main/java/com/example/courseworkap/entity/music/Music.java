package com.example.courseworkap.entity.music;

import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;

import java.util.Objects;

public abstract class Music {
    private int id;
    private ObservableStringValue name;
    private ObservableValue<Integer> duration;
    private ObservableStringValue style;

    protected Music(ObservableStringValue name, ObservableValue<Integer> duration,  ObservableStringValue style) {
        this.name = name;
        this.duration = duration;
        this.style = style;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ObservableStringValue getName() {
        return name;
    }

    public void setName(ObservableStringValue name) {
        this.name = name;
    }

    public ObservableValue<Integer> getDuration() {
        return duration;
    }

    public void setDuration(ObservableValue<Integer> duration) {
        this.duration = duration;
    }

    public  ObservableStringValue getStyle() {
        return style;
    }

    public void setStyle( ObservableStringValue style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Music music = (Music) o;

        if (id != music.id) return false;
        if (!Objects.equals(duration.getValue(), music.duration.getValue())) return false;
        if (name.toString() != null ? !name.toString().equals(music.name.toString()) : music.name.toString() != null) return false;
        return style.toString() != null ? style.toString().equals(music.style.toString()) : music.style.toString() == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name.toString() != null ? name.toString().hashCode() : 0);
        result = 31 * result + duration.getValue();
        result = 31 * result + (style.toString() != null ? style.toString().hashCode() : 0);
        return result;
    }
}
