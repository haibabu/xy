package com.babu.tet1.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), "Tet1",
				"Hello, Eclipse world");

		 Job job = new Job("My First Job") {
			protected IStatus run(IProgressMonitor monitor) {

				monitor.beginTask("New Task", 0);
				for (int i = 0; i < 1000000; i++) {
					monitor.subTask("Task..."+i);
					System.out.println(i/10000);
					monitor.worked(i/10000);
					new SubProgressMonitor(monitor, 1);
				
				}

				return Status.OK_STATUS;
			}
		};
		System.out.println(job.getState());
		job.setPriority(Job.SHORT);
		job.setUser(true);
		System.out.println(job.getState());
		job.schedule(); // start as soon as possible
		System.out.println(job.getState());
		Runnable rn= new Runnable() {
			
			@Override
			public void run() {
				System.out.println("t............");
			}
		};
		rn.run();
		return null;
	}
}
