package com.example.courseworkap.entity.music;

public abstract class Music {
    private int id;
    private String name;
    private int duration;
    private String style;

    protected Music(String name, int duration, String style) {
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

    public String getName() {
        return name;
    }
    public int getDuration() {
        return duration;
    }
    public String getStyle() {
        return style;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(System.lineSeparator()).append(duration).append(System.lineSeparator()).append(style).append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash=0;
        hash += name.hashCode();
        hash += duration*31;
        hash+=  style.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;}
        if(obj == null || getClass() != obj.getClass()){
            return false;}
        return hashCode() == obj.hashCode();
    }
}
