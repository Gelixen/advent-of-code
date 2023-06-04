package _2022.day2;

import static _2022.day2.HandShape.*;

public class Round {

    private final HandShape player1;
    private final HandShape player2;

    public Round(char player1, char player2) {
        this.player1 = fromSymbol(player1);
        this.player2 = fromSymbol(player2);
    }

    public int calculateScore() {
        return calculateWinningScore() + calculateShapeScore();
    }

    private int calculateWinningScore() {
        if (player1.equals(player2)) {
            return 3;
        } else if (player2winsAsRock() || player2winsAsScissors() || player2winsAsPaper()) {
            return 6;
        }
        return 0;
    }

    private boolean player2winsAsRock() {
        return ROCK.equals(player2) && SCISSORS.equals(player1);
    }

    private boolean player2winsAsScissors() {
        return SCISSORS.equals(player2) && PAPER.equals(player1);
    }


    private boolean player2winsAsPaper() {
        return PAPER.equals(player2) && ROCK.equals(player1);
    }

    private int calculateShapeScore() {
        return player2.getScore();
    }
}
