package by.kazlova.entity;

import java.io.Serializable;

public abstract class Entity implements Cloneable, Serializable {
    //private static final long serialVersionUID = 1L;
    protected long id; //protected

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    } //??


}
