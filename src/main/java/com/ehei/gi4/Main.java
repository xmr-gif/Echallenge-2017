
public class Main {

    public static void main(String[] args) {
        Train train = new Train("HPCPC");

        train.print();

        train.fill();
        train.print();

        train.fill();
        train.print();

        train.detachEnd();
        train.print();
    }
}

