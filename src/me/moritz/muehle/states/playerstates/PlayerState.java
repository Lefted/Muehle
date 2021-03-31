package me.moritz.muehle.states.playerstates;

import me.moritz.muehle.models.Point;

public interface PlayerState {

    void onClickedPoint(Point point);
    
    void onVoidClicked();
}
