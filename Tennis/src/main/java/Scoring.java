import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scoring {

    static int pointsPlayerA = 0;
    static int pointsPlayerB = 0;
    static int playerATieBreakWins = 0;
    static int playerBTieBreakWins = 0;
    static int playerAWinTieBreakSet = 0;
    static int playerBWinTieBreakSet = 0;

    public static void main(String[] args) throws IOException {


        String scoredPlayer = "";
        boolean scoreInTieBreak = false;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        while (playerAWinTieBreakSet < 2 && playerBWinTieBreakSet < 2) {
            while (!scoreInTieBreak) {
                System.out.println("Select winning player A or B");
                scoredPlayer = reader.readLine();
                if (scoredPlayer.equals("A") || scoredPlayer.equals("B")) {
                    if ("A".equals(scoredPlayer)) {
                        pointsPlayerA = pointsPlayerA + returnScore(pointsPlayerA);
                        if (pointsPlayerA > 40 && pointsPlayerB < 40) {
                            awardTieBreakPoint();
                            scoreInTieBreak = true;
                        }
                    } else {
                        pointsPlayerB = pointsPlayerB + returnScore(pointsPlayerB);
                        if (pointsPlayerB > 40 && pointsPlayerA < 40) {
                            awardTieBreakPoint();
                            scoreInTieBreak = true;
                        }
                    }
                    System.out.println("A player have: " + pointsPlayerA + " points. B player have: " + pointsPlayerB + " points");
                    if (pointsPlayerA >= 40 && pointsPlayerB >= 40) {
                        System.out.println("Reaches deuce");
                        if (Math.abs(pointsPlayerA - pointsPlayerB) == 20) {
                            awardTieBreakPoint();
                            scoreInTieBreak = true;
                        }
                    }
                    System.out.println("~~~ New serving ~~~");
                } else {
                    System.out.println("Wrong winning player, must be A or B");
                }
            }
            if (playerATieBreakWins >= 7 || playerBTieBreakWins >= 7) {
                if (Math.abs(playerATieBreakWins - playerBTieBreakWins) > 2) {
                    if (playerATieBreakWins > playerBTieBreakWins) {
                        playerAWinTieBreakSet = playerAWinTieBreakSet + 1;
                        System.out.println("A player Win the Set. A player have Set wins: " + playerAWinTieBreakSet);
                    } else {
                        playerBWinTieBreakSet = playerBWinTieBreakSet + 1;
                        System.out.println("B player Win the Set. B player have Set wins: " + playerBWinTieBreakSet);
                    }
                    System.out.println("New tie break set");
                    playerATieBreakWins = 0;
                    playerBTieBreakWins = 0;
                }
            }
            scoreInTieBreak = false;
        }
        if (playerAWinTieBreakSet > playerBWinTieBreakSet) {
            System.out.println("The winner is A!");
        } else {
            System.out.println("The winner is B!");
        }
    }

    static void awardTieBreakPoint() {
        if (pointsPlayerA > pointsPlayerB) {
            playerATieBreakWins = playerATieBreakWins + 1;
            System.out.println("A player Win the Tie Break.A have wins: " + playerATieBreakWins);
        } else {
            playerBTieBreakWins = playerBTieBreakWins + 1;
            System.out.println("B player Win the Tie Break.B have wins: " + playerBTieBreakWins);
        }
        pointsPlayerA = 0;
        pointsPlayerB = 0;
    }

    static int returnScore(int points) {
        if (points < 30) {
            return 15;
        } else {
            return 10;
        }
    }
}
