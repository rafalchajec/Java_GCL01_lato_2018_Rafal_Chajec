package User;

public class User {

    long id;
    String name;
    long numberOfStars;
    long numberOfSentMessages;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumberOfStars(int x) {
        if (x == 1) {
            this.numberOfStars++;
        } else {
            this.numberOfStars--;
        }
    }
    public User(String name) {
        this.name = name;
        this.numberOfStars = 0;
        this.numberOfSentMessages = 0;
    }

    public long getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfSentMessages(long numberOfSentMessages) {
        this.numberOfSentMessages = numberOfSentMessages;
    }

    public long getNumberOfSentMessages() {
        return numberOfSentMessages;
    }


}




