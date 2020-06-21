package by.kazlova.entity.impl;

public class HelpOffer {
    private Post post;
    private User volunteer;
    private HelpType helpType;
    private boolean isDone = false;

    public HelpOffer() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(User volunteer) {
        this.volunteer = volunteer;
    }

    public HelpType getHelpType() {
        return helpType;
    }

    public void setHelpType(HelpType helpType) {
        this.helpType = helpType;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((helpType == null) ? 0 : helpType.hashCode());
        result = prime * result + (isDone ? 1231 : 1237);
        result = prime * result + ((post == null) ? 0 : post.hashCode());
        result = prime * result + ((volunteer == null) ? 0 : volunteer.hashCode());
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
        HelpOffer other = (HelpOffer) obj;
        if (helpType == null) {
            if (other.helpType != null)
                return false;
        } else if (!helpType.equals(other.helpType))
            return false;
        if (isDone != other.isDone)
            return false;
        if (post == null) {
            if (other.post != null)
                return false;
        } else if (!post.equals(other.post))
            return false;
        if (volunteer == null) {
            if (other.volunteer != null)
                return false;
        } else if (!volunteer.equals(other.volunteer))
            return false;
        return true;
    }
}
