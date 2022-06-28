package com.geekbrains.lesson4;

public class Box {
    private  Integer ballsCount;

    public Box() {
        ballsCount = 0;
    }

    public  void addBall() {
        ballsCount++;
    }

    public void deleteBall() {
        if (ballsCount == 0) {
            throw new NullPointerException("Уже ноль мячей в коробке!");
        }
        ballsCount --;
    }

    public void shuffleBalls() throws ZeroBallsException {
        if (ballsCount == 0) {
            throw new ZeroBallsException();
        }
    }

    public Integer getBallsCount() {
        return ballsCount;
    }
}
