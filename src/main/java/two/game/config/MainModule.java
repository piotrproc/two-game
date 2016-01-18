package two.game.config;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

import io.vertx.core.Verticle;
import two.game.Server;
import two.game.communication.CommunicationServer;
import two.game.communication.EventDispatcher;
import two.game.communication.ResourcesServer;
import two.game.logic.GameState;
import two.game.logic.consumers.*;
import two.game.logic.predicates.ChangePredicate;
import two.game.logic.predicates.join.TeamAvailable;
import two.game.logic.predicates.join.UserIdAvailable;
import two.game.logic.predicates.state.NotAllowedIfStarted;
import two.game.logic.predicates.unitupdate.AttackPredicate;
import two.game.logic.predicates.unitupdate.MovePredicate;
import two.game.logic.predicates.unitupdate.UnitUpdatePredicate;
import two.game.logic.predicates.userupdate.UserPresent;
import two.game.logic.predicates.userupdate.UserSequenceIdNewer;
import two.game.logic.scheduled.ScheduledTasksRunner;
import two.game.model.init.JoinMatchRequest;
import two.game.model.update.SupportRequest;
import two.game.model.update.UnitUpdate;
import two.game.model.update.UserUpdate;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Server.class);
        //bind(GameState.class).asEagerSingleton(); // todo: (if multiple games are allowed) setup service supplying adequate state for given matchId

        Multibinder<Verticle> vertices = Multibinder.newSetBinder(binder(), Verticle.class);
        vertices.addBinding().to(CommunicationServer.class);
        vertices.addBinding().to(ResourcesServer.class);
        vertices.addBinding().to(EventDispatcher.class);
        vertices.addBinding().to(ScheduledTasksRunner.class);
        vertices.addBinding().to(GameState.class);

        Multibinder<ChangePredicate> join = Multibinder.newSetBinder(binder(), ChangePredicate.class, Names.named("JoinMatchRequest"));
        join.addBinding().to(NotAllowedIfStarted.class);
        join.addBinding().to(TeamAvailable.class);
        join.addBinding().to(UserIdAvailable.class);

        Multibinder<ChangePredicate> update = Multibinder.newSetBinder(binder(), ChangePredicate.class, Names.named("UserUpdate"));
        update.addBinding().to(UserPresent.class);
        update.addBinding().to(UserSequenceIdNewer.class);

        Multibinder<ChangePredicate> unitUpdate = Multibinder.newSetBinder(binder(), ChangePredicate.class, Names.named("UnitUpdate"));
        unitUpdate.addBinding().to(UnitUpdatePredicate.class);
        unitUpdate.addBinding().to(MovePredicate.class);
        unitUpdate.addBinding().to(AttackPredicate.class);

        MapBinder<Class, EventConsumer> consumers = MapBinder.newMapBinder(binder(), Class.class, EventConsumer.class);
        consumers.addBinding(JoinMatchRequest.class).to(JoinConsumer.class);
        consumers.addBinding(UserUpdate.class).to(UserUpdateConsumer.class);
        consumers.addBinding(UnitUpdate.class).to(UnitUpdateConsumer.class);
        consumers.addBinding(SupportRequest.class).to(SupportRequestConsumer.class);

        bind(new TypeLiteral<EventConsumer<UnitUpdate>>() {
        }).to(UnitUpdateConsumer.class);
    }
}
