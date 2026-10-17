
import com.ehei.gi4.Train;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TrainTest {

    // Captures what train.print() writes to the console.
    private String captureOutput(Train train) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output));
            train.print();
        } finally {
            System.setOut(originalOut);
        }

        return output.toString().strip();
    }

    private void assertTrainOutput(Train train, String expected) {
        assertEquals(expected, captureOutput(train));
    }

    @ParameterizedTest
    @CsvSource({
            "'HPP',   '<HHHH::|OOOO|::|OOOO|'",
            "'HPRP',  '<HHHH::|OOOO|::|hThT|::|OOOO|'",
            "'HCCC',  '<HHHH::|____|::|____|::|____|'",
            "'HPCPC', '<HHHH::|OOOO|::|____|::|OOOO|::|____|'",
            "'HPRPH', '<HHHH::|OOOO|::|hThT|::|OOOO|::HHHH>'"
    })
    void shouldPrintCorrectTrain(String config, String expected) {
        Train train = new Train(config);

        assertTrainOutput(train, expected);
    }

    @Test
    void shouldFillCargoFromLeftToRight() {
        Train train = new Train("HCCC");

        train.fill();
        assertTrainOutput(
                train,
                "<HHHH::|^^^^|::|____|::|____|"
        );

        train.fill();
        assertTrainOutput(
                train,
                "<HHHH::|^^^^|::|^^^^|::|____|"
        );

        train.fill();
        assertTrainOutput(
                train,
                "<HHHH::|^^^^|::|^^^^|::|^^^^|"
        );
    }

    @Test
    void shouldThrowWhenNoEmptyCargoRemains() {
        Train train = new Train("HC");

        train.fill();

        assertThrows(
                IllegalStateException.class,
                train::fill
        );
    }

    @Test
    void shouldDetachLastWagon() {
        Train train = new Train("HPRPH");

        train.detachEnd();

        assertTrainOutput(
                train,
                "<HHHH::|OOOO|::|hThT|::|OOOO|"
        );
    }

    @Test
    void shouldDetachFirstWagon() {
        Train train = new Train("HPRP");

        train.detachHead();

        assertTrainOutput(
                train,
                "|OOOO|::|hThT|::|OOOO|"
        );
    }

    @Test
    void shouldPreserveStateAfterMultipleOperations() {
        Train train = new Train("HPCPC");

        train.fill();
        train.detachEnd();

        assertTrainOutput(
                train,
                "<HHHH::|OOOO|::|^^^^|::|OOOO|"
        );
    }
}
