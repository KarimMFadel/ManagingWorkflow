package com.tornado.employeeStatusManager.general.config.workflow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.tornado.employeeStatusManager.dto.EmployeeEvents;
import com.tornado.employeeStatusManager.dto.EmployeeStates;
import com.tornado.employeeStatusManager.model.Employee;

@Component
public class EmployeeWorkFlowManager {
	
	@Autowired StateMachineFactory<EmployeeStates, EmployeeEvents> factory;

	public StateMachine<EmployeeStates, EmployeeEvents> newEmployee(Employee Employee) {
		StateMachine<EmployeeStates, EmployeeEvents> stateMachine = factory.getStateMachine("emp-" + Employee.getId());
		stateMachine.getExtendedState().getVariables().put("Employee", Employee);
		stateMachine.start();
		return stateMachine;
	}

	public boolean fire(StateMachine<EmployeeStates, EmployeeEvents> stateMachine, EmployeeEvents event) {
		return stateMachine.sendEvent(event);
	}

	@SuppressWarnings("unchecked")
	public List<Transition<EmployeeStates, EmployeeEvents>> getTransitions(
			StateMachine<EmployeeStates, EmployeeEvents> stateMachine) {
		List<Transition<EmployeeStates, EmployeeEvents>> transitions = new ArrayList<Transition<EmployeeStates, EmployeeEvents>>();

		for (Object objTrans : stateMachine.getTransitions()) {
			Transition<EmployeeStates, EmployeeEvents> transition = (Transition<EmployeeStates, EmployeeEvents>) objTrans;

			if (transition.getSource().getId().equals(stateMachine.getState().getId())) {
				transitions.add(transition);
			}
		}
		return transitions;
	}
}
