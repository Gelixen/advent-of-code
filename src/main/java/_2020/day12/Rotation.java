package _2020.day12;

public enum Rotation {
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    private final int rotationDegree;

    Rotation(int rotationDegree) {
        this.rotationDegree = rotationDegree;
    }

    public Rotation rotate(int degree) {
        int degreeDifference = this.rotationDegree + degree;
        int normalizedDegreeDifference = degreeDifference % 360;

        if (degreeDifference < 0) {
            normalizedDegreeDifference += 360;
        }

        return findRotationByDegree(normalizedDegreeDifference);
    }

    Rotation findRotationByDegree(int degree) {
        for (Rotation rotation : Rotation.values()) {
            if (rotation.rotationDegree == degree) {
                return rotation;
            }
        }
        throw new BadRotationDegreeException(degree);
    }
}
