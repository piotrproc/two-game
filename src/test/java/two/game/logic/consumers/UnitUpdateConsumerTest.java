package two.game.logic.consumers;

import com.google.common.collect.Lists;
import org.junit.Test;
import two.game.logic.GameState;
import two.game.model.Point;
import two.game.model.constant.MapElement;
import two.game.model.status.AttackEvent;
import two.game.model.status.MissileStatus;
import two.game.model.status.UnitStatus;
import two.game.model.update.MissileLaunch;
import two.game.model.update.UnitAttack;
import two.game.model.update.UnitUpdate;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UnitUpdateConsumerTest {

    @Test
    public void shouldSetMoveTarget() throws Exception {
        //given
        UnitUpdateConsumer consumer = new UnitUpdateConsumer(Collections.emptySet());
        Point defaultTarget = Point.of(0, 0);
        Point target = Point.of(99, 99);
        GameState state = getGameState();

        UnitStatus status = getUnitStatus();
        status.setUnitId(99L);
        status.setTargetPosition(defaultTarget);
        state.addUnit(status);

        UnitStatus otherStatus = getUnitStatus();
        otherStatus.setTargetPosition(defaultTarget);
        state.addUnit(otherStatus);

        UnitUpdate update = getUnitUpdate();
        update.setUnitId(99L);
        update.setMoveTarget(target);

        //when
        consumer.process(update, state);

        //then
        assertEquals(Point.of(99,99), status.getTargetPosition());
        assertEquals(defaultTarget, otherStatus.getTargetPosition());
        assertTrue(state.getUnitStatuses().contains(status));
        assertTrue(state.getUnitStatuses().contains(otherStatus));
    }

    @Test
    public void shouldAddAttackEvents() throws Exception {
        //given
        UnitUpdateConsumer consumer = new UnitUpdateConsumer(Collections.emptySet());

        GameState state = getGameState();
        UnitStatus status = getUnitStatus();
        status.setUnitId(99L);
        state.addUnit(status);

        UnitUpdate update = getUnitUpdate();
        update.setUnitId(99L);
        update.setAttacks(Lists.newArrayList(new UnitAttack(44L), new UnitAttack(77L)));

        //when
        consumer.process(update, state);

        //then
        assertTrue(state.getAttackEvents().contains(new AttackEvent(99L, 44L)));
        assertTrue(state.getAttackEvents().contains(new AttackEvent(99L, 77L)));
    }

    @Test
    public void shouldAddMissileLaunches() throws Exception {
        //given
        UnitUpdateConsumer consumer = new UnitUpdateConsumer(Collections.emptySet());
        Point unitPosition = Point.of(22, 22);

        GameState state = getGameState();
        UnitStatus status = getUnitStatus();
        status.setUnitId(99L);
        status.setPosition(unitPosition);
        state.addUnit(status);

        UnitUpdate update = getUnitUpdate();
        update.setUnitId(99L);
        update.setMissileLaunches(Lists.newArrayList(new MissileLaunch(Point.of(100, 100)), new MissileLaunch(Point.of(200, 200))));

        //when
        consumer.process(update, state);

        //then
        List<MissileStatus> missileStatuses = state.getMissileStatuses();
        assertEquals(2, missileStatuses.size());
        missileStatuses.stream().anyMatch(missileStatus ->
                missileStatus.getTargetPosition().equals(Point.of(100, 100)) &&
                        missileStatus.getCurrentPosition().equals(unitPosition)
        );
        missileStatuses.stream().anyMatch(missileStatus ->
                missileStatus.getTargetPosition().equals(Point.of(200, 200)) &&
                        missileStatus.getCurrentPosition().equals(unitPosition)
        );

    }

    private UnitUpdate getUnitUpdate() {
        return new UnitUpdate(0L, Point.of(0, 0), Collections.emptyList(), Collections.emptyList());
    }

    private UnitStatus getUnitStatus() {
        return new UnitStatus(0L, 0, "test_user", 0, 0, 0, 0, 0, Point.of(0, 0), Point.of(0, 0));
    }

    private GameState getGameState() {
        return new GameState((x, y) -> MapElement.GROUND, new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
    }
}