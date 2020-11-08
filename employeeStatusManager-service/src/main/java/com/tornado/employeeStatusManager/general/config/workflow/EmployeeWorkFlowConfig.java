package com.tornado.employeeStatusManager.general.config.workflow;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.tornado.employeeStatusManager.dto.EmployeeEvents;
import com.tornado.employeeStatusManager.dto.EmployeeStates;
import com.tornado.employeeStatusManager.general.config.kafka.Producer;
import com.tornado.employeeStatusManager.model.Employee;

@Configuration
@EnableStateMachineFactory
public class EmployeeWorkFlowConfig extends EnumStateMachineConfigurerAdapter<EmployeeStates, EmployeeEvents>{

	@Autowired
	Producer producer;
	
	@Override
    public void configure(StateMachineStateConfigurer<EmployeeStates, EmployeeEvents> states) throws Exception {
        states
            .withStates()
                .initial(EmployeeStates.ADDED)
                .state( EmployeeStates.IN_CHECK, entryAction(), exitAction() )
                .state( EmployeeStates.APPROVED, entryAction(), exitAction() )
                .end(EmployeeStates.ACTIVE)
                .end(EmployeeStates.CANCELLED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EmployeeStates, EmployeeEvents> transitions) throws Exception {
        transitions
            .withExternal()
                .source(EmployeeStates.ADDED)
                .target(EmployeeStates.IN_CHECK)
                .event(EmployeeEvents.IN_CHECK)
                //.guard( budgetGuard( BigDecimal.valueOf( 100 ) ) )
                .and()
            .withExternal()
                .source(EmployeeStates.APPROVED)
                .target(EmployeeStates.ACTIVE)
                .event(EmployeeEvents.ACTIVE)
                .action( activeAction() )
                .and()
	        .withExternal()
		        .source(EmployeeStates.IN_CHECK)
		        .target(EmployeeStates.APPROVED)
		        .event(EmployeeEvents.APPROVE)
                .action( approveAction() )
		        .and()
	        .withExternal()
		        .source(EmployeeStates.APPROVED)
		        .target(EmployeeStates.CANCELLED)
		        .event(EmployeeEvents.CANCEL)
		        .action( cancelAction() );
    }
    
    //------------------------------------------------------------------------------------------------------
    // Actions
    //------------------------------------------------------------------------------------------------------
    
    
    public Action<EmployeeStates, EmployeeEvents> activeAction() {
        return new Action<EmployeeStates, EmployeeEvents>() {
			
			public void execute(StateContext<EmployeeStates, EmployeeEvents> context) {
				Employee employee = findEmployee( context.getExtendedState() );
				if ( employee != null ) {
					employee.setActivateDate( new Date() );
					producer.sendMessage(EmployeeStates.ACTIVE, employee.getId());
				}
			}
        };
    }
    
    
    public Action<EmployeeStates, EmployeeEvents> approveAction() {
        return new Action<EmployeeStates, EmployeeEvents>() {
			
			public void execute(StateContext<EmployeeStates, EmployeeEvents> context) {
				Employee employee = findEmployee( context.getExtendedState() );
				if ( employee != null ) {
					producer.sendMessage(EmployeeStates.APPROVED, employee.getId());
				}
			}
        };
    }
    
    public Action<EmployeeStates, EmployeeEvents> cancelAction() {
        return new Action<EmployeeStates, EmployeeEvents>() {
			
			public void execute(StateContext<EmployeeStates, EmployeeEvents> context) {
				Employee employee = findEmployee( context.getExtendedState() );
				if ( employee != null ) {
					producer.sendMessage(EmployeeStates.CANCELLED, employee.getId());
				}
			}
        };
    }
    
    //------------------------------------------------------------------------------------------------------
    // entryAction and exitAction
    //------------------------------------------------------------------------------------------------------
    
    
    public Action<EmployeeStates, EmployeeEvents> entryAction() {
        return new Action<EmployeeStates, EmployeeEvents>() {
			
			public void execute(StateContext<EmployeeStates, EmployeeEvents> context) {
				System.out.println( "Entering state " + context.getTarget().getId() );
			}
        };
    }
    
    public Action<EmployeeStates, EmployeeEvents> exitAction() {
        return new Action<EmployeeStates, EmployeeEvents>() {
			
			public void execute(StateContext<EmployeeStates, EmployeeEvents> context) {
				System.out.println( "Exiting state " + context.getSource().getId() );
			}
        };
    }
    
 //------------------------------------------------------------------------------------------------------
    
    private Employee findEmployee( ExtendedState extendedState ) {
    	for ( Object obj : extendedState.getVariables().values() ) {
			if ( obj instanceof Employee ) {
				return (Employee) obj;
			}
		}
    	return null;
    }
}
