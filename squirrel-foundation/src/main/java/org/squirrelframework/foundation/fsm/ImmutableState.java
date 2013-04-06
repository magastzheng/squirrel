package org.squirrelframework.foundation.fsm;

import java.util.List;

import org.squirrelframework.foundation.component.SquirrelComponent;

/**
 * <p><b>State</b> The basic unit that composes a state machine. A state machine can be in one state at 
 * any particular time.</p>
 * <p><b>Entry Action</b> An activity executed when entering the state</p>
 * <p><b>Entry Action</b> An activity executed when entering the state</p>
 * <p><b>Final State</b> A state which represents the completion of the state machine.</p>
 * 
 * @author Henry.He
 *
 * @param <T> type of State Machine
 * @param <S> type of State
 * @param <E> type of Event
 * @param <C> type of Context
 */
public interface ImmutableState<T extends StateMachine<T, S, E, C>, S, E, C>  extends Visitable<T, S, E, C>, SquirrelComponent {
    
    /**
     * @return state id
     */
    S getStateId();
    
    /**
     * @return Activities executed when entering the state
     */
    List<Action<T, S, E, C>> getEntryActions();
    
    /**
     * @return Activities executed when exiting the state
     */
    List<Action<T, S, E, C>> getExitActions();
    
    /**
     * @return All transitions start from this state
     */
    List<ImmutableTransition<T, S, E, C>> getAllTransitions();
    
    /**
     * @param event 
     * @return Transitions triggered by event
     */
    List<ImmutableTransition<T, S, E, C>> getTransitions(E event);
    
    /**
     * Entry state with state context
     * @param stateContext
     */
    void entry(StateContext<T, S, E, C> stateContext);
    
    ImmutableState<T, S, E, C> enterByHistory(StateContext<T, S, E, C> stateContext);
    
    /**
     * Enters this state is shallow mode: The entry action is executed and the
	 * initial state is entered in shallow mode if there is one.
	 * @param stateContext
	 * @return child state entered by shadow
     */
    ImmutableState<T, S, E, C> enterShallow(StateContext<T, S, E, C> stateContext);
    
    /**
     * Exit state with state context
     * @param stateContext
     */
    void exit(StateContext<T, S, E, C> stateContext);
    
    /**
     * @return parent state
     */
    ImmutableState<T, S, E, C> getParentState();
    
    
    /**
     * @return initial child state
     */
    ImmutableState<T, S, E, C> getChildInitialState();
    
    /**
     * Notify transitions when receiving event.
     * @param stateContext
     * @return result of transition
     */
    TransitionResult<T, S, E, C> internalFire(StateContext<T, S, E, C> stateContext);
    
    /**
     * @return whether current state is final state
     */
    boolean isFinal();
    
    /**
     * @return hierarchy state level
     */
    int getLevel();
}