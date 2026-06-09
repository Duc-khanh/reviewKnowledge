public class TennisGame {

    private static final int LOVE = 0;
    private static final int FIFTEEN = 1;
    private static final int THIRTY = 2;
    private static final int FORTY = 3;
    private static final int MIN_SCORE_TO_WIN = 4;

    public static String getScore(String player1Name, String player2Name, int player1Score, int player2Score) {
        if (player1Score == player2Score) {
            return getEqualScore(player1Score);
        }

        if (player1Score >= MIN_SCORE_TO_WIN || player2Score >= MIN_SCORE_TO_WIN) {
            return getAdvantageOrWinScore(player1Score, player2Score);
        }

        return getNormalScore(player1Score, player2Score);
    }

    private static String getEqualScore(int score) {
        switch (score) {
            case LOVE:
                return "Love-All";
            case FIFTEEN:
                return "Fifteen-All";
            case THIRTY:
                return "Thirty-All";
            case FORTY:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }

    private static String getAdvantageOrWinScore(int player1Score, int player2Score) {
        int scoreDifference = player1Score - player2Score;

        if (scoreDifference == 1) {
            return "Advantage player1";
        }

        if (scoreDifference == -1) {
            return "Advantage player2";
        }

        if (scoreDifference >= 2) {
            return "Win for player1";
        }

        return "Win for player2";
    }

    private static String getNormalScore(int player1Score, int player2Score) {
        return getScoreName(player1Score) + "-" + getScoreName(player2Score);
    }

    private static String getScoreName(int score) {
        switch (score) {
            case LOVE:
                return "Love";
            case FIFTEEN:
                return "Fifteen";
            case THIRTY:
                return "Thirty";
            case FORTY:
                return "Forty";
            default:
                return "";
        }
    }
}