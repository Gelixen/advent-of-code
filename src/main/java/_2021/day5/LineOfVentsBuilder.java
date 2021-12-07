package _2021.day5;

public class LineOfVentsBuilder {
    public static LineOfVents build(String lineOfVentsDescription) {
        String[] positions = lineOfVentsDescription.split(" -> ");

        String[] startPositionCoords = positions[0].split(",");
        String[] endPositionCoords = positions[1].split(",");

        Position startPosition = new Position(extractXCoord(startPositionCoords), extractYCoord(startPositionCoords));
        Position endPosition = new Position(extractXCoord(endPositionCoords), extractYCoord(endPositionCoords));

        return new LineOfVents(startPosition, endPosition);
    }

    private static int extractXCoord(String[] positionCoords) {
        return convertToInt(positionCoords[0]);
    }

    private static int extractYCoord(String[] positionCoords) {
        return convertToInt(positionCoords[1]);
    }

    private static int convertToInt(String startPositionCoords) {
        return Integer.parseInt(startPositionCoords);
    }
}
