/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.decorator.gmf.commands.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.modelversioning.emfprofile.application.decorator.gmf.Decorator;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ApplyStereotypeHandler extends AbstractHandler implements
		IHandler {

	public ApplyStereotypeHandler() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if(Decorator.getApplyStereotypeListener() != null){
			ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
			if(currentSelection != null && currentSelection instanceof IStructuredSelection){
				IStructuredSelection structuredSelection = (IStructuredSelection) currentSelection;
				EditPart editPart = (EditPart) structuredSelection.getFirstElement();
				Object model = editPart.getModel();
				if (model instanceof Node) {
					Node node = (Node) model;
					EObject selectedEObject = node.getElement();
					
					System.out.println("Sending apply stereotype notification!");
					Decorator.getApplyStereotypeListener().applyStereotype(selectedEObject);
				} else {
					System.err.println("model from edit part is not an instance of Node!");
				}	
			}
		}else {
			System.err.println("There is no Apply Stereotype Listener registered");
		}
		return null;
	}

}