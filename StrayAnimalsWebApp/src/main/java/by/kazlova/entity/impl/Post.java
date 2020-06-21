package by.kazlova.entity.impl;

import by.kazlova.entity.Entity;

import java.util.Date;

public class Post extends Entity {
    private String title;
    private String text;
    private Date date; // ??
    private boolean isModerated = false; // ??
    private boolean isActual = true; // true ??
    private User user;

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isModerated() {
        return isModerated;
    }

    public void setModerated(boolean isModerated) {
        this.isModerated = isModerated;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean isActual) {
        this.isActual = isActual;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + (isActual ? 1231 : 1237);
        result = prime * result + (isModerated ? 1231 : 1237);
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (isActual != other.isActual)
            return false;
        if (isModerated != other.isModerated)
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Post [title=" + title + ", text=" + text + ", date=" + date + ", isModerated=" + isModerated
                + ", isActual=" + isActual + ", user=" + user + "]";
    }

}
