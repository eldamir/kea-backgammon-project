package model.board;

import java.util.ArrayList;

import model.piece.Piece;


/**
 * @version 1.1
 * @since 2014-05-08
 */
public class BoardState
{
    /**
     * this is an arraylist that contains the state of the board
     * <p/>
     * the index 0 is reserved for the white home position
     * and the rest of the fields get the rest of the
     * indexes counter-clockwise.
     * <p/>
     * the field in the middle is separated in two fields one for the user and
     * the other for the computer.
     */
    private ArrayList[] board = new ArrayList[28];

    private static boolean turn = true;

    /**
     * Initialize default state
     */
    public BoardState()
    {
        for (int i = 0; i < this.board.length; i++)
        {
            this.board[i] = new ArrayList<Piece>();
        }

        for (int i = 1; i <= 2; i++)
        {
            this.board[23].add(new Piece(false, 23));
        }

        for (int i = 1; i <= 5; i++)
        {
            this.board[18].add(new Piece(true, 18));
        }

        for (int i = 1; i <= 3; i++)
        {
            this.board[16].add(new Piece(true, 16));
        }

        for (int i = 1; i <= 5; i++)
        {
            this.board[12].add(new Piece(false, 12));
        }

        for (int i = 1; i <= 5; i++)
        {
            this.board[11].add(new Piece(true, 11));
        }

        for (int i = 1; i <= 3; i++)
        {
            this.board[7].add(new Piece(false, 7));
        }

        for (int i = 1; i <= 5; i++)
        {
            this.board[5].add(new Piece(false, 5));
        }

        for (int i = 1; i <= 2; i++)
        {
            this.board[0].add(new Piece(true, 0));
        }
    }

    /**
     * @param current boardState gets updated
     * @param Move    are used to get the piece which will be moved to a chosen spike
     */
    public BoardState(BoardState previousBoardState, Move newMove)
    {
        int endSpikePos = newMove.getPosition();
        Piece pieceToBeMoved = newMove.getPiece();
        int currentSpikePos = pieceToBeMoved.getBoardPlacement();


        // Copy the state of the previous board
        for (int i = 0; i <= 27; i++)
        {

            ArrayList<Piece> spike = previousBoardState.board[i];
            board[i].addAll(spike);
        }

        // Remove piece from current position
        board[currentSpikePos].remove(pieceToBeMoved);

        // Add piece to new position
        board[endSpikePos].add(pieceToBeMoved);

        // Update piece position variable
        pieceToBeMoved.setBoardPlacement(endSpikePos);
    }


    /**
     * @return current state of the board until updated
     */
    public ArrayList[] getBoard()
    {
        return this.board;
    }

    /**
     * @param Turn
     * @return if true then user is playing, if false computer is playing
     */
    public void shiftTurn()
    {
        this.turn = !turn;
    }

    public ArrayList<Piece> getSpike(int n)
    {
        ArrayList<Piece> spike = board[n];
        return spike;
    }

}
